package com.example.dddstudy.order.application;

import com.example.dddstudy.menu.domain.MenuRepository;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public void validate(Order order) {
        // TODO: validate
    }
}
