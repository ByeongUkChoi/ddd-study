package com.example.dddstudy.product.domain;

import java.util.List;

public class OptionGroup {
    private Long id;
    private String name;
    private long price;
    private boolean isRequired;
    private Integer limitQuantity;  // null is unlimited
    private List<OptionItem> OptionItems;
}
