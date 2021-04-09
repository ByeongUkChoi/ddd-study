package com.example.dddstudy.order.domain;

public class OrderOptionItem {
    private long optionItemId;
    private long price;
    private int quantity;

    public OrderOptionItem(long optionItemId, long price, int quantity) {
        this.optionItemId = optionItemId;
        this.price = price;
        this.quantity = quantity;
    }
}
