package com.example.dddstudy.menu.domain;

import lombok.Getter;

import java.util.List;

public class OptionGroup {
    @Getter
    private Long id;
    private String name;
    @Getter
    private long price;
    private boolean isRequired;
    private Integer limitQuantity;  // null is unlimited
    @Getter
    private List<OptionItem> OptionItems;
}
