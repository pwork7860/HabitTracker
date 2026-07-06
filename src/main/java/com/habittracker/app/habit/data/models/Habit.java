package com.habittracker.app.habit.data.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Habit {
    private String id;
    private String name;
    private String frequency;
}
