package com.example.dddstudy.order.application;

import com.example.dddstudy.menu.domain.Menu;
import com.example.dddstudy.menu.domain.MenuRepository;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.store.domain.Store;
import com.example.dddstudy.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public void validate(Order order) {
        Store store = storeRepository.findById(order.getStoreId())
                .orElseThrow(IllegalArgumentException::new);
        if (store.isOrderable() == false) {
            throw new IllegalArgumentException("해당 가게가 주문 불가능한 상태입니다.");
        }
        Set<Long> orderedMenuIds = order.getOrderItems().stream().map(OrderItem::getMenuId).collect(Collectors.toSet());
        // menu id => menu
        Map<Long, Menu> menuMap = menuRepository.findAllById(orderedMenuIds).stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));

        // TODO: price validate
    }
}
