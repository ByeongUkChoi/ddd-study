package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
    private final OrderMapper orderMapper;
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public void placeOrder(long ordererId, OrderRequest orderRequest) {
        Order order = orderMapper.mapFrom(ordererId, orderRequest);
        order.place(orderValidator);
        orderRepository.save(order);
    }

    public void cancelOrder() {

    }
}
