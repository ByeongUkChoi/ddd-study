package com.example.dddstudy.order;

import java.util.List;

public class Order {
    private Orderer orderer;
    private List<OrderItem> orderItems;
    private DeliveryInfo deliveryInfo;
    private Status status;

    public void order(Orderer orderer, List<OrderItem> orderItems, DeliveryInfo deliveryInfo) {
    }

    public void cancel() {
        if (Status.WAITING.equals(status)) {
            status = Status.CANCELED;
        }
        throw new RuntimeException();
    }

    enum Status {
        WAITING, PREPARING, DELIVERING, DELIVERED, CANCELED
    }
}
