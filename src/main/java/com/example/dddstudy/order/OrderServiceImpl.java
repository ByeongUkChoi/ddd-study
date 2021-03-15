package com.example.dddstudy.order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void order() {
        new Order();
    }

    @Override
    public void cancelOrder() {

    }

    @Override
    public List<Object> getOrderSnippets() {
        return null;
    }

    @Override
    public List<Object> getOrderDetails() {
        return null;
    }
}
