package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.DeliveryInfo;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;

public class OrderCommandServiceTest {
    private final OrderCommandService orderCommandService;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public OrderCommandServiceTest() {
        orderMapper = mock(OrderMapper.class);
        orderValidator = mock(OrderValidator.class);
        orderRepository = mock(OrderRepository.class);
        orderCommandService = new OrderCommandService(orderMapper, orderValidator, orderRepository);
    }

    @Test
    public void createOrderSuccessTest() {
        // given
        final long ordererId = 1;
        final long storeId = 5;
        final String address = "seoul";
        final String message = "-";
        final String phone = "010-1234-1234";
        final long menuId = 11;
        final long price = 10_000;
        final int quantity = 2;
        final long optionGroupId = 3;
        final long optionItemId = 5;
        final long optionItemPrice = 700;

        OrderRequest.OrderOptionItem orderOptionItem = new OrderRequest.OrderOptionItem(optionItemId,optionItemPrice);
        OrderRequest.OrderOptionGroup orderOptionGroup = new OrderRequest.OrderOptionGroup(optionGroupId, orderOptionItem);
        OrderRequest.OrderItem orderItem = new OrderRequest.OrderItem(menuId, price, quantity, orderOptionGroup);
        OrderRequest.DeliveryInfo deliveryInfo = new OrderRequest.DeliveryInfo(address, message, phone);
        OrderRequest orderRequest = new OrderRequest(storeId, deliveryInfo, orderItem);

        DeliveryInfo deliveryInfo1 = new DeliveryInfo(address, message, phone);
        OrderOptionItem orderOptionItem1 = new OrderOptionItem(optionItemId, optionItemPrice);
        OrderOptionGroup orderOptionGroup1 = new OrderOptionGroup(optionGroupId, orderOptionItem1);
        OrderItem orderItem1 = new OrderItem(menuId, price, quantity, orderOptionGroup1);
        Order expectedOrder = new Order(ordererId, storeId, deliveryInfo1, orderItem1);

        when(orderMapper.mapFrom(anyLong(), eq(orderRequest))).thenReturn(expectedOrder);

        // when
        orderCommandService.placeOrder(ordererId, orderRequest);

        // then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        final Order order = orderCaptor.getValue();
        assertThat(order, notNullValue());
        assertThat(getField(order, "ordererId"), is(ordererId));
        assertThat(getField(order, "storeId"), is(storeId));
        assertThat(getField(order, "status"), is(Order.Status.WAITING));

        DeliveryInfo actualDeliveryInfo = (DeliveryInfo) getField(order, "deliveryInfo");
        assertThat(getField(actualDeliveryInfo, "address"), is(address));
        assertThat(getField(actualDeliveryInfo, "message"), is(message));
        assertThat(getField(actualDeliveryInfo, "phone"), is(phone));

        List<OrderItem> actualOrderItems = (List<OrderItem>) getField(order, "orderItems");
        assertThat(actualOrderItems.size(), is(1));
        OrderItem actualOrderItem1 = actualOrderItems.get(0);
        assertThat(getField(actualOrderItem1, "menuId"), is(menuId));
        assertThat(getField(actualOrderItem1, "price"), is(price));
        assertThat(getField(actualOrderItem1, "quantity"), is(quantity));

        List<OrderOptionGroup> orderOptionGroups = (List<OrderOptionGroup>) getField(actualOrderItem1, "orderOptionGroups");
        assertThat(orderOptionGroups.size(), is(1));
        OrderOptionGroup actualOrderOptionGroup = orderOptionGroups.get(0);
        assertThat(getField(actualOrderOptionGroup, "optionGroupId"), is(optionGroupId));

        List<OrderOptionItem> orderOptionItems = (List<OrderOptionItem>) getField(actualOrderOptionGroup, "orderOptionItems");
        assertThat(orderOptionItems.size(), is(1));

        OrderOptionItem actualOrderOptionItem = orderOptionItems.get(0);
        assertThat(getField(actualOrderOptionItem, "optionItemId"), is(optionItemId));
        assertThat(getField(actualOrderOptionItem, "price"), is(optionItemPrice));
    }
}
