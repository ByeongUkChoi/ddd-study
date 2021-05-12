package com.example.dddstudy.store.domain;

import java.util.Arrays;
import java.util.List;

public class StoreHours {
    private List<DailyStoreHours> dailyStoreHoursList;

    public StoreHours(DailyStoreHours... dailyStoreHoursList) {
        this.dailyStoreHoursList = Arrays.asList(dailyStoreHoursList);
    }
}
