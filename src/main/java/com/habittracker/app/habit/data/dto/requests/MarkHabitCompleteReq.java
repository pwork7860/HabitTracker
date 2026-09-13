package com.habittracker.app.habit.data.dto.requests;

import com.habittracker.app.commons.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkHabitCompleteReq {
    private Status status;
}
