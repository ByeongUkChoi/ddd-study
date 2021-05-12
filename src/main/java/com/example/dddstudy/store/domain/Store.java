package com.example.dddstudy.store.domain;

public class Store {
    private Long id;
    private String name;
    private boolean isOpened;
    private StoreHours storeHours;

    public Store(String name, boolean isOpened, StoreHours storeHours) {
        this.name = name;
        this.isOpened = isOpened;
        this.storeHours = storeHours;
    }
}
