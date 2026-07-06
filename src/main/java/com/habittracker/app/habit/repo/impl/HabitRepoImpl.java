package com.habittracker.app.habit.repo.impl;

import com.habittracker.app.habit.data.models.Habit;
import com.habittracker.app.habit.repo.iface.HabitRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitRepoImpl implements HabitRepo {

    static List<Habit> habits = new ArrayList<>();

    @Override
    public boolean createhabitRequest(Habit habit) {
        return habits.add(habit);
    }

    @Override
    public List<Habit> getHabits() {
        return habits;
    }
}
