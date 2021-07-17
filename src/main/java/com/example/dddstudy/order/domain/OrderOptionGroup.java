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

@Entity(name = "order_option_groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private long optionGroupId;
    @OneToMany
    private List<OrderOptionItem> orderOptionItems;

    public OrderOptionGroup(long optionGroupId, OrderOptionItem... orderOptionItems) {
        this.optionGroupId = optionGroupId;
        this.orderOptionItems = Arrays.asList(orderOptionItems);
    }

    public long getTotalOptionItemPrice() {
        return orderOptionItems.stream()
                .mapToLong(OrderOptionItem::getPrice)
                .sum();
    }
}
