package com.example.dddstudy.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private long menuId;

    @Getter
    private long price;     // 단가 (메뉴의 1개 가격)

    private int quantity;

    @OneToMany
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
