package com.example.dddstudy.store.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreHours {
    @OneToMany(cascade = CascadeType.ALL)
    private List<DailyStoreHours> dailyStoreHoursList;

    public StoreHours(DailyStoreHours... dailyStoreHoursList) {
        this.dailyStoreHoursList = Arrays.asList(dailyStoreHoursList);
    }
}
