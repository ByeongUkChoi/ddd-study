package com.example.dddstudy.order.domain;

import java.util.List;

public class OrderItem {
    private long productId;
    private long price;
    private long quantity;
    private List<OrderOptionGroup> orderOptionGroups;
}
