package com.habittracker.app.habit.controllers;

import com.habittracker.app.commons.dto.response.ApiResponse;
import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.requests.UpdateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitResponse;
import com.habittracker.app.habit.data.dto.response.MarkHabitCompleteResponse;
import com.habittracker.app.habit.svc.iface.HabitSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("habit/v1")
@RequiredArgsConstructor
public class HabitController {
    private final HabitSvc habitSvc;

    @PostMapping("/habits")
    public ResponseEntity<ApiResponse<CreateHabitResponse>> createHabit(
            @Valid @RequestBody CreateHabitRequest request) {
        CreateHabitResponse response = habitSvc.createhabit(request);
        URI location = URI.create("/habits/" + response.getId());
        return ResponseEntity.created(location)
                .body(ApiResponse.success("Habit Created Successfully", response));
    }


    @GetMapping("/habits")
    public ResponseEntity<ApiResponse<List<HabitResponse>>> fetchHabits() {
        List<HabitResponse> habits = habitSvc.fetchHabits();
        return ResponseEntity.ok(ApiResponse.success("Habits Fetched Successfuly", habits));
    }

    @GetMapping("/habits/{id}")
    public ResponseEntity<ApiResponse<HabitResponse>> getHabit(@PathVariable String id) {
        HabitResponse habitResponse = habitSvc.getHabit(id);
        return ResponseEntity.ok(ApiResponse.success("Habit fetched successfully", habitResponse));
    }

    @PutMapping("/habits/{id}")
    public ResponseEntity<ApiResponse<CreateHabitResponse>> updateHabit(
            @RequestBody UpdateHabitRequest request,
            @PathVariable String id) {
        CreateHabitResponse habitResponse = habitSvc.updateHabit(request, id);
        return ResponseEntity.ok(ApiResponse.success("Habit updated successfully", habitResponse));
    }

    @DeleteMapping("/habits/{id}")
    public ResponseEntity<ApiResponse<String>> deleteHabit(@PathVariable String id) {
        String message = habitSvc.deleteHabit(id);
        return ResponseEntity.ok(ApiResponse.success(message, message));

    }

    @PutMapping("/habits/{id}/complete")
    public ResponseEntity<ApiResponse<
            MarkHabitCompleteResponse>> markHabitComplete(@PathVariable String id) {
        MarkHabitCompleteResponse markHabitCompleteResponse = habitSvc.markHabitComplete(id);
        return ResponseEntity.ok(ApiResponse.success("Habit Marked Completed",
                markHabitCompleteResponse));
    }

}
