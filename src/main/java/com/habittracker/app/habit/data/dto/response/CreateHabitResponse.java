package com.habittracker.app.habit.data.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateHabitResponse {
    private String id;
    private String name;
    private String frequency;
}
