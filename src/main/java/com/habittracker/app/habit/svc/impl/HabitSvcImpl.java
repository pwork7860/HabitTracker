package com.habittracker.app.habit.svc.impl;

import com.habittracker.app.commons.dto.response.ApiResponse;
import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.requests.UpdateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitResponse;
import com.habittracker.app.habit.data.dto.response.MarkHabitCompleteResponse;
import com.habittracker.app.habit.data.models.Habit;
import com.habittracker.app.habit.data.models.HabitCompletion;
import com.habittracker.app.habit.eception.DuplicateHabitNameException;
import com.habittracker.app.habit.eception.HabitNotFoundException;
import com.habittracker.app.habit.repo.iface.HabitCompletionrepo;
import com.habittracker.app.habit.repo.iface.HabitRepo;
import com.habittracker.app.habit.svc.iface.HabitSvc;
import com.habittracker.app.habit.utils.HabitAdapter;
import com.habittracker.app.jwt.svc.utils.AuthenticationUtils;
import com.habittracker.app.user.data.model.User;
import com.habittracker.app.user.repo.iface.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitSvcImpl implements HabitSvc {

    private final HabitRepo habitRepo;
    private final HabitCompletionrepo habitCompletionRepo;
    private final UserRepo userRepo;

    @Override
    public CreateHabitResponse createhabit(CreateHabitRequest request) {
        String userid = AuthenticationUtils.getUserIdFromAuth();
        if(habitRepo.findByUserIdAndNameAndStatus(
                userid, request.getName(), Status.ACTIVE).isPresent()) {
            throw new DuplicateHabitNameException(request.getName());
        }
        Habit habit = HabitAdapter.getHabit(request, userid);
        habitRepo.save(habit);
        return HabitAdapter.getCreatHabitResponse(habit);
    }

    @Override
    public List<HabitResponse> fetchHabits() {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        User user = userRepo.findById(userId).orElseThrow();
        List <HabitResponse> habitsResponse = new ArrayList<>();
        List<Habit> habits =  habitRepo.findByUserIdAndStatus(userId, Status.ACTIVE);
        if (CollectionUtils.isEmpty(habits)) {
            return habitsResponse;
        }
        LocalDate today = LocalDate.now(ZoneId.of(user.getTimeZone()));
        List<HabitCompletion> habitsCompletedToday =
                habitCompletionRepo.findByUserIdAndCompletedDate(userId, today);
        Set<String> completedHabitIds = habitsCompletedToday.stream()
                .map(HabitCompletion::getHabitId)
                .collect(Collectors.toSet());
            for (Habit item : habits) {
                habitsResponse.add(HabitResponse.builder()
                        .id(item.getId())
                        .frequency(item.getFrequency())
                        .name(item.getName())
                        .completedToday(
                                completedHabitIds.contains(item.getId()))
                        .build());
            }
            return habitsResponse;
        }


    @Override
    public HabitResponse getHabit(String id) {
        String userid = AuthenticationUtils.getUserIdFromAuth();
        Optional<Habit> habit =
                habitRepo.findByIdAndUserIdAndStatus(id, userid, Status.ACTIVE);
        if (habit.isEmpty()) {
            throw new HabitNotFoundException(id);
        }
        return HabitResponse.builder()
                .id(habit.get().getId())
                .frequency(habit.get().getFrequency())
                .name(habit.get().getName())
                .build();


    }

    @Override
    public CreateHabitResponse updateHabit(UpdateHabitRequest request, String id) {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        Optional<Habit> habit = habitRepo.findByIdAndUserIdAndStatus(id, userId, Status.ACTIVE);
        if (habit.isEmpty()) {
            throw new HabitNotFoundException(id);
        }
        if (request.getName() != null) {
            Optional<Habit> existingHabit =
                    habitRepo.findByUserIdAndNameAndStatus(userId,
                            request.getName(), Status.ACTIVE);
            if (existingHabit.isPresent()
                    && !existingHabit.get().getId().equals(id)) {
                throw new DuplicateHabitNameException(request.getName());
            }
            habit.get().setName(request.getName());
        }
        if (request.getFrequency() != null) {
            habit.get().setFrequency(request.getFrequency());
        }
        Habit updatedHabit = habitRepo.save(habit.get());
        return CreateHabitResponse.builder()
                .id(updatedHabit.getId())
                .frequency(updatedHabit.getFrequency())
                .name(updatedHabit.getName())
                .build();
    }

    @Override
    public String deleteHabit(String id) {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        Optional<Habit> habit = habitRepo.findByIdAndUserIdAndStatus(id, userId, Status.ACTIVE);
        if (habit.isEmpty()) {
            throw new HabitNotFoundException(id);
        }
        Habit existingHabit = habit.get();
        existingHabit.setStatus(Status.INACTIVE);
        habitRepo.save(existingHabit);
        return "Habit Deleted Successfully";
    }

    @Override
    public MarkHabitCompleteResponse markHabitComplete(String id) {

        String userId = AuthenticationUtils.getUserIdFromAuth();
        User user = userRepo.findById(userId).orElseThrow();
        Optional<Habit> habit = habitRepo.findByIdAndUserIdAndStatus(
                id, userId, Status.ACTIVE);
        if (habit.isEmpty()) {
            throw new HabitNotFoundException(id);
        }
        HabitCompletion completion = HabitAdapter.getHabitCompletion(habit.get(),
                user.getTimeZone());
        habitCompletionRepo.save(completion);
        return MarkHabitCompleteResponse.builder()
                .build();
    }
}
