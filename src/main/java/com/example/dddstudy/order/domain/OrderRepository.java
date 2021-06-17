package com.example.dddstudy.order.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
    Optional<Order> findById(long id);
    Order save(Order order);
}
