package com.example.dddstudy.order.application;

import com.example.dddstudy.global.error.exception.BusinessException;
import com.example.dddstudy.global.error.exception.ErrorCode;
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
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ORDER));
        if (store.isOrderable() == false) {
            throw new BusinessException(ErrorCode.STORE_IS_NOT_OPEN);
        }
        Set<Long> orderedMenuIds = order.getOrderItems().stream()
                .map(OrderItem::getMenuId).collect(Collectors.toSet());

        List<Menu> menus = menuRepository.findAllById(orderedMenuIds);
        // menu id => menu
        Map<Long, Menu> menuMap = menus.stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));

        order.getOrderItems().stream()
                .forEach(orderItem -> {
                    if (!menuMap.containsKey(orderItem.getMenuId())) {
                        throw new BusinessException(ErrorCode.NOT_FOUND_MENU);
                    }
                    validateOrderItem(orderItem, menuMap.get(orderItem.getMenuId()));
                });
    }

    private void validateOrderItem(OrderItem orderItem, Menu menu) {
        if (!menu.isOrderable()) {
            throw new BusinessException(ErrorCode.CANNOT_ORDERED_MENU);
        }
        if (orderItem.getPrice() != menu.getPrice()) {
            throw new BusinessException(ErrorCode.INVALID_MENU_PRICE);
        }

        Map<Long, OptionGroup> optionGroupMap = menu.getOptionGroups().stream()
                .collect(Collectors.toMap(OptionGroup::getId, Function.identity()));

        orderItem.getOrderOptionGroups().stream()
                .forEach(orderOptionGroup -> {
                    if (!optionGroupMap.containsKey(orderOptionGroup.getOptionGroupId())) {
                        throw new BusinessException(ErrorCode.NOT_FOUND_OPTION_MENU_GROUP);
                    }
                    validateOrderOptionGroup(orderOptionGroup, optionGroupMap.get(orderOptionGroup.getOptionGroupId()));
                });
    }

    private void validateOrderOptionGroup(OrderOptionGroup orderOptionGroup, OptionGroup optionGroup) {
        Map<Long, OptionItem> optionItemMap = optionGroup.getOptionItems().stream()
                .collect(Collectors.toMap(OptionItem::getId, Function.identity()));

        orderOptionGroup.getOrderOptionItems().stream()
                .forEach(orderOptionItem -> {
                    if (!optionItemMap.containsKey(orderOptionItem.getOptionItemId())) {
                        throw new BusinessException(ErrorCode.NOT_FOUND_OPTION_MENU_ITEM);
                    }
                    validateOrderOptionItem(orderOptionItem, optionItemMap.get(orderOptionItem.getOptionItemId()));
                });
    }

    private void validateOrderOptionItem(OrderOptionItem orderOptionItem, OptionItem optionItem) {
        if (orderOptionItem.getPrice() != optionItem.getPrice()) {
            throw new BusinessException(ErrorCode.INVALID_OPTION_MENU_ITEM_PRICE);
        }
    }
}
