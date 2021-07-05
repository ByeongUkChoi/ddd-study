package com.example.dddstudy.order.domain;

public class OrderStartedDeliveryEvent {
    private Order order;

    public OrderStartedDeliveryEvent(Order order) {
        this.order = order;
    }
}
