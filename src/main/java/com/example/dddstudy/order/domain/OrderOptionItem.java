package com.example.dddstudy.order.domain;

import lombok.Getter;

@Getter
public class OrderOptionItem {
    private long optionItemId;
    private long price;

    public OrderOptionItem(long optionItemId, long price) {
        this.optionItemId = optionItemId;
        this.price = price;
    }
}
