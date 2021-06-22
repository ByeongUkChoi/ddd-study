package com.example.dddstudy.store.application;

import com.example.dddstudy.order.domain.OrderPayedEvent;
import com.example.dddstudy.order.domain.OrderPlacedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderPayedEventHandler {

    @TransactionalEventListener
    public void handle(OrderPayedEvent event) {
        // TODO
        System.out.println("주문 결제가 완료되었습니다.");
        // TODO: make
    }
}
