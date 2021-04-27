package com.example.dddstudy.order.application;

import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.Orderer;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    /**
     * dto to entity
     */
    public Order mapFrom(Orderer orderer, OrderRequest orderRequest) {
        return null;
    }
}
