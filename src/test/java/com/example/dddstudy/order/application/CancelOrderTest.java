package com.example.dddstudy.order.application;

import com.example.dddstudy.global.error.exception.BusinessException;
import com.example.dddstudy.global.error.exception.ErrorCode;
import com.example.dddstudy.order.domain.DeliveryInfo;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class CancelOrderTest {
    private final OrderCommandService orderCommandService;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public CancelOrderTest() {
        orderMapper = mock(OrderMapper.class);
        orderValidator = mock(OrderValidator.class);
        orderRepository = mock(OrderRepository.class);
        orderCommandService = new OrderCommandService(orderMapper, orderValidator, orderRepository);
    }

    @Test
    public void cancelOrderSuccessTest() {
        // given
        long ordererId = 2;
        long orderId = 1;
        Order order = createOrder(ordererId);
        setField(order, "id", orderId);
        setField(order, "status", Order.Status.ORDERED);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        // when
        orderCommandService.cancelOrder(ordererId, orderId);

        // then
        // ... success
    }

    @Test
    public void cancelOrderStatusFailureTest() {
        // given
        long ordererId = 2;
        long orderId = 1;
        Order order = createOrder(ordererId);
        setField(order, "id", orderId);
        setField(order, "status", Order.Status.DELIVERING);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            orderCommandService.cancelOrder(ordererId, orderId);
        });

        // then
        assertEquals(getField(exception, "errorCode"), ErrorCode.ORDER_CANNOT_BE_CANCELED);
    }

    private Order createOrder(long ordererId) {
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
