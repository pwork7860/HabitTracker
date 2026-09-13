package com.habittracker.app.habit.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum Frequency {
    DAILY, WEEKLY, MONHLY, YEARLY;

    private static final List<Frequency> frequencies =
            List.of(Frequency.values());
}
