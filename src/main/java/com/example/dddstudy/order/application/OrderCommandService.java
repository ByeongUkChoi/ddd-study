package com.example.dddstudy.order.application;

import com.example.dddstudy.global.error.exception.BusinessException;
import com.example.dddstudy.global.error.exception.ErrorCode;
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

    public void payOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ORDER));
        order.pay();
    }

    public void cancelOrder(long ordererId, long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ORDER));
        order.cancel(ordererId);
    }
}
