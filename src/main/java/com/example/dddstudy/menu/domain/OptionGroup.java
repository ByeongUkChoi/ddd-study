package com.example.dddstudy.menu.domain;

import lombok.Getter;

import java.util.List;

public class OptionGroup {
    @Getter
    private Long id;
    private String name;
    private boolean isRequired;
    private Integer limitQuantity;  // null is unlimited
    @Getter
    private List<OptionItem> OptionItems;
}
