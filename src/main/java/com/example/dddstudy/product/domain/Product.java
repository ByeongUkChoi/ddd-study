package com.example.dddstudy.product.domain;

public class Product {
    private String name;
    private long storeId;
    private long price;
    private Option option;
    private boolean orderable;

    private Product(String name, long storeId, long price, Option option, boolean orderable) {
        this.name = name;
        this.storeId = storeId;
        this.price = price;
        this.option = option;
        this.orderable = orderable;
    }
}
