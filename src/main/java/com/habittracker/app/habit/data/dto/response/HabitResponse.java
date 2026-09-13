package com.habittracker.app.habit.data.dto.response;

import com.habittracker.app.habit.enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitResponse {
    private String id;
    private String name;
    private Frequency frequency;
    private boolean completedToday;
}
