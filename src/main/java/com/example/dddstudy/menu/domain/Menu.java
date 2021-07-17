package com.example.dddstudy.menu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Entity(name = "menus")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    private String name;
    private long storeId;
    @Getter
    private long price;
    private boolean orderable;
    @OneToMany(cascade = CascadeType.ALL)
    @Getter
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

    /** 주문 가능 여부 */
    public boolean isOrderable() {
        return orderable;
    }
}
