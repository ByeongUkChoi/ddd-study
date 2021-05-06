package com.example.dddstudy.order.domain;

import lombok.Getter;

public class OrderOptionItem {
    private long optionItemId;
    @Getter
    private long price;

    public OrderOptionItem(long optionItemId, long price) {
        this.optionItemId = optionItemId;
        this.price = price;
    }
}
