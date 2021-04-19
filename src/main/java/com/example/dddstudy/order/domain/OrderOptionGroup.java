package com.example.dddstudy.order.domain;

import java.util.Arrays;
import java.util.List;

public class OrderOptionGroup {
    private long optionGroupId;
    private int maxOptionItemCount;   // 옵션 품목 최대 수량
    private List<OrderOptionItem> orderOptionItems;

    public OrderOptionGroup(long optionGroupId, int maxOptionItemCount, OrderOptionItem... orderOptionItems) {
        this.optionGroupId = optionGroupId;
        this.maxOptionItemCount = maxOptionItemCount;
        this.orderOptionItems = Arrays.asList(orderOptionItems);
    }
}
