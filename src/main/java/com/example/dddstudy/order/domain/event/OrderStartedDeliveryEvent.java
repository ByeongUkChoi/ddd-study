package com.example.dddstudy.order.domain.event;

import com.example.dddstudy.order.domain.Order;

public class OrderStartedDeliveryEvent {
    private Order order;

    public OrderStartedDeliveryEvent(Order order) {
        this.order = order;
    }
}
