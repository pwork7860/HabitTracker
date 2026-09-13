package com.habittracker.app.habit.data.dto.requests;

import com.habittracker.app.habit.enums.Frequency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHabitRequest {

    @NotBlank(message = "Habit name cannot be blank")
    @Size(min = 3, message = "Habit name must contain at least 3 characters")
    private String name;

    @NotNull(message = "Frequency is required")
    private Frequency frequency;
}
