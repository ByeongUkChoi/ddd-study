package com.example.dddstudy.product.domain;

public class Product {
    private String name;
    private long storeId;
    private long price;
    private Option option;
    private boolean orderable;

    private Product(String name, long storeId, long price, Option option, boolean orderable) {
        this.name = name;
        this.storeId = storeId;
        this.price = price;
        this.option = option;
        this.orderable = orderable;
    }

    /** 주문 가능하게 설정 */
    public void changeOrderable() {
        orderable = true;
    }
    /** 주문 불가능하게 설정 */
    public void changeUnorderable() {
        orderable = false;
    }
}
