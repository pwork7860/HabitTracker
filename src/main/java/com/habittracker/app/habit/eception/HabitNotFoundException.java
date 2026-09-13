package com.habittracker.app.habit.eception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HabitNotFoundException extends RuntimeException{

    public HabitNotFoundException(String habitId) {
        super("habit not found with id: " + habitId);
    }

}
