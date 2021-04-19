package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.getField;

@ExtendWith(MockitoExtension.class)
public class OrderCommandServiceTest {

    @InjectMocks
    private final OrderCommandService orderCommandService;
    @Mock
    private final OrderRepository orderRepository;

    public OrderCommandServiceTest(OrderCommandService orderCommandService,
                                   OrderRepository orderRepository) {
        this.orderCommandService = orderCommandService;
        this.orderRepository = orderRepository;
    }

    public void createOrderSuccessTest() {
        // given
        final long ordererId = 1;
        final String address = "seoul";
        final String message = "-";
        final String phone = "010-1234-1234";
        final long menuId = 11;
        final long price = 10_000;
        final int quantity = 2;
        final long optionGroupId = 3;
        final int maxOptionItemCount = 0;
        final long optionItemId = 5;
        final long optionItemPrice = 700;

        OrderRequestDto.OrderOptionItem orderOptionItem = new OrderRequestDto.OrderOptionItem(optionItemId,optionItemPrice);
        OrderRequestDto.OrderOptionGroup orderOptionGroup = new OrderRequestDto.OrderOptionGroup(optionGroupId, orderOptionItem);
        OrderRequestDto.OrderItem orderItem1 = new OrderRequestDto.OrderItem(menuId, price, quantity, orderOptionGroup);
        OrderRequestDto.DeliveryInfo deliveryInfo = new OrderRequestDto.DeliveryInfo(address, message, phone);
        OrderRequestDto orderRequestDto = new OrderRequestDto(ordererId, deliveryInfo, orderItem1);

        // when
        orderCommandService.order(orderRequestDto);

        // then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());

        final Order order = orderCaptor.getValue();
        assertThat(order, notNullValue());
        assertThat(getField(order, "ordererId"), is(ordererId));
        assertThat(getField(order, "status"), is(Order.Status.PREPARING));

        OrderRequestDto.DeliveryInfo actualDeliveryInfo = (OrderRequestDto.DeliveryInfo) getField(order, "deliveryInfo");
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
        assertThat(getField(actualOrderOptionGroup, "maxOptionItemCount"), is(maxOptionItemCount));

        List<OrderOptionItem> orderOptionItems = (List<OrderOptionItem>) getField(actualOrderOptionGroup, "orderOptionItems");
        assertThat(orderOptionItems.size(), is(1));

        OrderOptionItem actualOrderOptionItem = orderOptionItems.get(0);
        assertThat(getField(actualOrderOptionItem, "optionItemId"), is(optionItemId));
        assertThat(getField(actualOrderOptionItem, "price"), is(optionItemPrice));

        // TODO: event
    }
}
