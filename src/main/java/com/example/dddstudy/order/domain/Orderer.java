package com.example.dddstudy.order.domain;

public class Orderer {
    private long memberId;
    private String name;

    public Orderer(long ordererId, String name) {
        this.memberId = ordererId;
        this.name = name;
    }
}
