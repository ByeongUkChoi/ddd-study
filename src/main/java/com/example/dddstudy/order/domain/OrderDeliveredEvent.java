package com.example.dddstudy.order.domain;

public class OrderDeliveredEvent {
    private Order order;

    public OrderDeliveredEvent(Order order) {
        this.order = order;
    }
}
