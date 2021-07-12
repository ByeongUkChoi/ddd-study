package com.example.dddstudy.order.domain.event;

import com.example.dddstudy.order.domain.Order;

public class OrderCanceledEvent {
    private Order order;

    public OrderCanceledEvent(Order order) {
        this.order = order;
    }
}
