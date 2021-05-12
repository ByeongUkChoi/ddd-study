package com.example.dddstudy.store.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DailyStoreHours {
    private DayOfWeek dayOfWeek;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

    public DailyStoreHours(DayOfWeek dayOfWeek, LocalDateTime openingTime, LocalDateTime closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
}
