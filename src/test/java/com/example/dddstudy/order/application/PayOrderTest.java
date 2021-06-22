package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.OrderRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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
        // TODO: orderRepository inject Order entity mock
        // TODO: assert
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

}
