package com.example.dddstudy.order.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
