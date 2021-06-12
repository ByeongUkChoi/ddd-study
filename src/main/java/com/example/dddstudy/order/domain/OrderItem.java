package com.example.dddstudy.order.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class OrderItem {
    @Getter
    private long menuId;
    @Getter
    private long price;     // 단가 (메뉴의 1개 가격)
    private int quantity;
    @Getter
    private List<OrderOptionGroup> orderOptionGroups;

    public OrderItem(long menuId, long price, int quantity, OrderOptionGroup... orderOptionGroups) {
        this.menuId = menuId;
        this.price = price;
        this.quantity = quantity;
        this.orderOptionGroups = Arrays.asList(orderOptionGroups);
    }

    /**
     * 최종 가격 : (단가 + 옵션 총 가격) * 수량
     * @return
     */
    public long getFinalPrice() {
        return (price + getTotalOptionPrice()) * quantity;
    }

    public long getTotalOptionPrice() {
        return orderOptionGroups.stream()
                .mapToLong(OrderOptionGroup::getTotalOptionItemPrice)
                .sum();
    }
}
