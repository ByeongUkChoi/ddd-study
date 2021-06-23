package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.DeliveryInfo;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class PayOrderTest {
    private final OrderCommandService orderCommandService;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public PayOrderTest() {
        orderMapper = mock(OrderMapper.class);
        orderValidator = mock(OrderValidator.class);
        orderRepository = mock(OrderRepository.class);
        orderCommandService = new OrderCommandService(orderMapper, orderValidator, orderRepository);
    }

    @Test
    public void payOrderSuccessTest() {
        // given
        long orderId = 1;
        Order order = createOrder();
        setField(order, "id", orderId);
        setField(order, "status", Order.Status.ORDERED);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        // when
        orderCommandService.payOrder(orderId);

        // given
        // ... success
    }

    @Test
    public void payOrderStatusFailureTest() {
        // TODO: orderRepository inject Order entity mock
        // TODO: assert
    }

    @Test
    public void payOrderNotFoundOrderFailureTest() {
        // TODO: orderRepository inject Order entity mock
        // TODO: assert
    }

    private Order createOrder() {
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

        DeliveryInfo deliveryInfo1 = new DeliveryInfo(address, message, phone);
        OrderOptionItem orderOptionItem1 = new OrderOptionItem(optionItemId, optionItemPrice);
        OrderOptionGroup orderOptionGroup1 = new OrderOptionGroup(optionGroupId, orderOptionItem1);
        OrderItem orderItem1 = new OrderItem(menuId, price, quantity, orderOptionGroup1);
        return new Order(ordererId, storeId, deliveryInfo1, orderItem1);
    }

}
