package com.habittracker.app.habit.svc.iface;

import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitsResponse;
import com.habittracker.app.habit.data.models.Habit;

import java.util.List;

public interface HabitSvc {
    CreateHabitResponse createhabit(CreateHabitRequest request);

    List<HabitsResponse> fetchHabits();
}
