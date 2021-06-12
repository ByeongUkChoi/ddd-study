package com.example.dddstudy.order.application;

import com.example.dddstudy.menu.domain.Menu;
import com.example.dddstudy.menu.domain.MenuRepository;
import com.example.dddstudy.menu.domain.OptionGroup;
import com.example.dddstudy.menu.domain.OptionItem;
import com.example.dddstudy.order.domain.Order;
import com.example.dddstudy.order.domain.OrderItem;
import com.example.dddstudy.order.domain.OrderOptionGroup;
import com.example.dddstudy.order.domain.OrderOptionItem;
import com.example.dddstudy.store.domain.Store;
import com.example.dddstudy.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
        Set<Long> orderedMenuIds = order.getOrderItems().stream()
                .map(OrderItem::getMenuId).collect(Collectors.toSet());

        List<Menu> menus = menuRepository.findAllById(orderedMenuIds);
        // menu id => menu
        Map<Long, Menu> menuMap = menus.stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));

        order.getOrderItems().stream()
                .forEach(orderItem -> {
                    validateOrderItem(orderItem, menuMap.get(orderItem.getMenuId()));
                });
    }

    private void validateOrderItem(OrderItem orderItem,  Menu menu) {
        if (orderItem.getMenuId() != menu.getId()) {
            throw new IllegalArgumentException("주문 상품 오류입니다.");
        }
        if (!menu.isOrderable()) {
            // TODO: custom error
            throw new IllegalArgumentException("해당 메뉴는 주문이 불가능 합니다." + menu.getId());
        }
        if (orderItem.getPrice() != menu.getPrice()) {
            throw new IllegalArgumentException("해당 메뉴의 금액이 일치하지 않습니다." + menu.getId());
        }

        Map<Long, OptionGroup> optionGroupMap = menu.getOptionGroups().stream()
                .collect(Collectors.toMap(OptionGroup::getId, Function.identity()));
        orderItem.getOrderOptionGroups().stream()
                .forEach(orderOptionGroup -> validateOrderOptionGroup(orderOptionGroup, optionGroupMap.get(orderOptionGroup.getOptionGroupId())));
    }

    private void validateOrderOptionGroup(OrderOptionGroup orderOptionGroup, OptionGroup optionGroup) {
        if (orderOptionGroup.getOptionGroupId() != optionGroup.getId()) {
            throw new IllegalArgumentException("주문 옵션 그룹 오류입니다.");
        }
        Map<Long, OptionItem> optionItemMap = optionGroup.getOptionItems().stream()
                .collect(Collectors.toMap(OptionItem::getId, Function.identity()));

        orderOptionGroup.getOrderOptionItems().stream()
                .forEach(orderOptionItem -> validateOrderOptionItem(orderOptionItem, optionItemMap.get(orderOptionItem.getOptionItemId())));
    }

    private void validateOrderOptionItem(OrderOptionItem orderOptionItem, OptionItem optionItem) {
        if (orderOptionItem.getOptionItemId() != optionItem.getPrice()) {
            throw new IllegalArgumentException("주문 옵션 상품 오류입니다.");
        }
        if (orderOptionItem.getPrice() != optionItem.getPrice()) {
            throw new IllegalArgumentException("주문 옵션 상품 금액 오류입니다.");
        }
    }
}
