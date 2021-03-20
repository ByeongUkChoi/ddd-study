package com.example.dddstudy.order.domain;

public interface OrderRepository {
    Order findById(long id);
    Order save(Order order);
}
