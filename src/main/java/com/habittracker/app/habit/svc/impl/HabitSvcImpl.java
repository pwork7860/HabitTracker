package com.habittracker.app.habit.svc.impl;

import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.requests.UpdateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitResponse;
import com.habittracker.app.habit.data.models.Habit;
import com.habittracker.app.habit.repo.iface.HabitRepo;
import com.habittracker.app.habit.svc.iface.HabitSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitSvcImpl implements HabitSvc {

    private final HabitRepo habitRepo;

    @Override
    public CreateHabitResponse createhabit(CreateHabitRequest request) {

        Habit habit = Habit.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .frequency(request.getFrequency())
                .build();

        if (habitRepo.createhabitRequest(habit)) {
            return CreateHabitResponse.builder()
                    .id(habit.getId())
                    .name(habit.getName())
                    .frequency(habit.getFrequency())
                    .build();
        }
        return CreateHabitResponse.builder().build();
    }

    @Override
    public List<HabitResponse> fetchHabits() {
        List<Habit> habits=  habitRepo.getHabits();
        List <HabitResponse> habitsResponses = new ArrayList<>();
        if (CollectionUtils.isEmpty(habits)) {
            return habitsResponses;
        } else {
            for (Habit item : habits) {
                habitsResponses.add(HabitResponse.builder()
                        .id(item.getId())
                        .frequency(item.getFrequency())
                        .name(item.getName())
                        .build());
            }
            return habitsResponses;
        }
    }

    @Override
    public HabitResponse getHabit(String id) {
        Habit habit = habitRepo.getHabitById(id);
//        if (habit == null) {
//            throw new
//        }
        HabitResponse habitResponse = HabitResponse.builder()
                .id(habit.getId())
                .frequency(habit.getFrequency())
                .name(habit.getName())
                .build();

        return habitResponse;


    }

    @Override
    public CreateHabitResponse updateHabit(UpdateHabitRequest request, String id) {
        CreateHabitResponse  habitResponse = CreateHabitResponse.builder().build();
        Habit habit = habitRepo.getHabitById(id);
        if (habit != null) {
            if (request.getFrequency() != null) {
                habit.setFrequency(request.getFrequency());
            }
            if (request.getName() != null) {
                habit.setName(request.getName());
            }
            habitRepo.updateHabit(habit);
            habitResponse.setId(habit.getId());
            habitResponse.setFrequency(habit.getFrequency());
            habitResponse.setName(habit.getName());
        }
        return habitResponse;
    }

    @Override
    public String deleteHabit(String id) {
        Habit habit = habitRepo.getHabitById(id);
        if (habit != null) {
            habitRepo.deleteHabit(id);
            return "Habit Deleted Successfully";
        }
        return "No Hbait found for Id : " + id;
    }
}
