package com.example.dddstudy.order.domain.event;

import com.example.dddstudy.order.domain.Order;

public class OrderPayedEvent {
    private Order order;

    public OrderPayedEvent(Order order) {
        this.order = order;
    }
}
