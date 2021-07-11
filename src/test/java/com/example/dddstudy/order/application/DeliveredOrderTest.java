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

public class DeliveredOrderTest {
    private final OrderCommandService orderCommandService;
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public DeliveredOrderTest() {
        orderMapper = mock(OrderMapper.class);
        orderValidator = mock(OrderValidator.class);
        orderRepository = mock(OrderRepository.class);
        orderCommandService = new OrderCommandService(orderMapper, orderValidator, orderRepository);
    }

    @Test
    public void startDeliveryOrderSuccessTest() {
        // given
        long orderId = 1;
        Order order = createOrder();
        setField(order, "id", orderId);
        setField(order, "status", Order.Status.DELIVERING);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        // when
        orderCommandService.deliveredOrder(orderId);

        // then
        // ... success
    }

    @Test
    public void startDeliveryOrderStatusFailureTest() {
        // given
        long orderId = 1;
        Order order = createOrder();
        setField(order, "id", orderId);

        when(orderRepository.findById(eq(orderId))).thenReturn(Optional.of(order));

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            orderCommandService.deliveredOrder(orderId);
        });

        // then
        assertEquals(getField(exception, "errorCode"), ErrorCode.INVALID_ORDER_STATUS_TO_DELIVERED);
    }

    @Test
    public void startDeliveryOrderNotFoundOrderFailureTest() {
        // given
        long orderId = 1;

        // when
        Exception exception = assertThrows(BusinessException.class, () -> {
            orderCommandService.deliveredOrder(orderId);
        });

        // then
        assertEquals(getField(exception, "errorCode"), ErrorCode.NOT_FOUND_ORDER);
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
