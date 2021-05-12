package com.example.dddstudy.menu.domain;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private Long id;
    private String name;
    private long storeId;
    private long price;
    private boolean orderable;
    private List<OptionGroup> optionGroups;

    public Menu(String name, long storeId, long price, boolean orderable, OptionGroup... optionGroups) {
        this.name = name;
        this.storeId = storeId;
        this.price = price;
        this.orderable = orderable;
        this.optionGroups = Arrays.asList(optionGroups);
    }

    /** 주문 가능하게 설정 */
    public void changeOrderable(long storeId) {
        orderable = true;
    }
    /** 주문 불가능하게 설정 */
    public void changeUnorderable(long storeId) {
        orderable = false;
    }
}
