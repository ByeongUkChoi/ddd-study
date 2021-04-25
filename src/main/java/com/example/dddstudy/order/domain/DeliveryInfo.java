package com.example.dddstudy.order.domain;

public class DeliveryInfo {
    private String address;
    private String message;
    private String phone;

    public DeliveryInfo(String address, String message, String phone) {
        this.address = address;
        this.message = message;
        this.phone = phone;
    }
}
