package com.example.dddstudy.store.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "stores")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isOpened;
    @Embedded
    private StoreHours storeHours;

    public Store(String name, boolean isOpened, StoreHours storeHours) {
        this.name = name;
        this.isOpened = isOpened;
        this.storeHours = storeHours;
    }

    public boolean isOrderable() {
        if (isOpened == false) {
            return false;
        }
        // TODO: store houres
        return true;
    }
}
