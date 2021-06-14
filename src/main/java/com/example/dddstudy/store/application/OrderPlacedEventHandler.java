package com.example.dddstudy.store.application;

import com.example.dddstudy.order.domain.OrderPlacedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderPlacedEventHandler {

    @TransactionalEventListener
    public void handle(OrderPlacedEvent event) {
        // TODO
        System.out.println("주문이 들어왔습니다.");
    }
}
