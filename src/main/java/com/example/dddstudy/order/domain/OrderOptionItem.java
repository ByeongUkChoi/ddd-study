package com.example.dddstudy.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "order_option_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderOptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long optionItemId;
    private long price;

    public OrderOptionItem(long optionItemId, long price) {
        this.optionItemId = optionItemId;
        this.price = price;
    }
}
