package com.example.dddstudy.order.domain;

import java.util.Arrays;
import java.util.List;

public class OrderItem {
    private long menuId;
    private long price;
    private int quantity;
    private List<OrderOptionGroup> orderOptionGroups;

    public OrderItem(long menuId, long price, int quantity, OrderOptionGroup... orderOptionGroups) {
        this.menuId = menuId;
        this.price = price;
        this.quantity = quantity;
        this.orderOptionGroups = Arrays.asList(orderOptionGroups);
    }
}
