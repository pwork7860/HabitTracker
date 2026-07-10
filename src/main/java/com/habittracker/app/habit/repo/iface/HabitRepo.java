package com.habittracker.app.habit.repo.iface;

import com.habittracker.app.habit.data.models.Habit;

import java.util.List;

public interface HabitRepo {

    boolean createhabitRequest(Habit habit);

    List<Habit> getHabits();

    Habit getHabitById(String id);

    boolean updateHabit(Habit habit);

    boolean deleteHabit(String id);
}
