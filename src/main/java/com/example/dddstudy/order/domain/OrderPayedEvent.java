package com.example.dddstudy.order.domain;

public class OrderPayedEvent {
    private Order order;

    public OrderPayedEvent(Order order) {
        this.order = order;
    }
}
