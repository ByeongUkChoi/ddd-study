package com.example.dddstudy.order;

import java.util.List;

public class Order {
    private Orderer orderer;
    private List<OrderItem> orderItems;
    private DeliveryInfo deliveryInfo;
    private Status status;

    public void order(Orderer orderer, List<OrderItem> orderItems, ShippingInfo shippingInfo) {
    }

    enum Status {
        WAITING, PREPARING, DELIVERING, DELIVERED, CANCELED
    }
}
