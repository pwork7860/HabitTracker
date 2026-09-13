package com.habittracker.app.habit.data.dto.response;

import com.habittracker.app.habit.enums.Frequency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHabitResponse {
    private String id;
    private String name;
    private Frequency frequency;
}
