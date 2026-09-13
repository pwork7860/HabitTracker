package com.habittracker.app.habit.eception;

public class DuplicateHabitNameException extends RuntimeException{

    public DuplicateHabitNameException(String name) {
        super("habit already exists wth name: " + name);
    }

}
