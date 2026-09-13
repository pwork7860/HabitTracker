package com.habittracker.app.habit.svc.iface;

import com.habittracker.app.commons.dto.response.ApiResponse;
import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.requests.UpdateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitResponse;
import com.habittracker.app.habit.data.dto.response.MarkHabitCompleteResponse;
import com.habittracker.app.habit.data.models.Habit;

import java.util.List;

public interface HabitSvc {
    CreateHabitResponse createhabit(CreateHabitRequest request);

    List<HabitResponse> fetchHabits();

    HabitResponse getHabit(String id);

    CreateHabitResponse updateHabit(UpdateHabitRequest request, String id);

    String deleteHabit(String id);

    MarkHabitCompleteResponse markHabitComplete(String id);
}
