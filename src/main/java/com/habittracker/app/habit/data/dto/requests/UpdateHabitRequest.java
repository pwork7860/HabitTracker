package com.habittracker.app.habit.data.dto.requests;

import com.habittracker.app.habit.enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHabitRequest {
    private String name;
    private Frequency frequency;
}
