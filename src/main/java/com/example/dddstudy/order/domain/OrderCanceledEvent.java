package com.example.dddstudy.order.domain;

public class OrderCanceledEvent {
    private Order order;

    public OrderCanceledEvent(Order order) {
        this.order = order;
    }
}
