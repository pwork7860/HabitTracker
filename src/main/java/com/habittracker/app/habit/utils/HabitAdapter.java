package com.habittracker.app.habit.utils;

import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.models.Habit;
import com.habittracker.app.habit.data.models.HabitCompletion;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;


public class HabitAdapter {

    public static Habit getHabit(CreateHabitRequest request, String userId) {
        return Habit.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .frequency(request.getFrequency())
                .userId(userId)
                .status(Status.ACTIVE)
                .build();
    }

    public static CreateHabitResponse getCreatHabitResponse(Habit habit) {
        return CreateHabitResponse.builder()
                .id(habit.getId())
                .name(habit.getName())
                .frequency(habit.getFrequency())
                .build();
    }

    public static HabitCompletion getHabitCompletion(Habit habit, String timeZone) {
        Instant completedAt = Instant.now();
        ZoneId zoneId = ZoneId.of(timeZone);
        LocalDate completedDate =
                completedAt.atZone(zoneId).toLocalDate();
        return HabitCompletion.builder()
                .habitId(habit.getId())
                .completedDate(completedDate)
                .completedAt(completedAt)
                .createdAt(Instant.now())
                .userId(habit.getUserId())
                .build();
    }
}
