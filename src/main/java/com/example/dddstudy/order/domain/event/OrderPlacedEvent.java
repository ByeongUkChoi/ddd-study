package com.example.dddstudy.order.domain.event;

import com.example.dddstudy.order.domain.Order;

public class OrderPlacedEvent {
    private Order order;

    public OrderPlacedEvent(Order order) {
        this.order = order;
    }
}
