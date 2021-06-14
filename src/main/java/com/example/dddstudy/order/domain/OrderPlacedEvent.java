package com.example.dddstudy.order.domain;

public class OrderPlacedEvent {
    private Order order;

    public OrderPlacedEvent(Order order) {
        this.order = order;
    }
}
