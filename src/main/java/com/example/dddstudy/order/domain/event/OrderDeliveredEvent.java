package com.example.dddstudy.order.domain.event;

import com.example.dddstudy.order.domain.Order;

public class OrderDeliveredEvent {
    private Order order;

    public OrderDeliveredEvent(Order order) {
        this.order = order;
    }
}
