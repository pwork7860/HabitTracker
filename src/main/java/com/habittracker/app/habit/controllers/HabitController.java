package com.habittracker.app.habit.controllers;

import com.habittracker.app.habit.data.dto.requests.CreateHabitRequest;
import com.habittracker.app.habit.data.dto.requests.UpdateHabitRequest;
import com.habittracker.app.habit.data.dto.response.CreateHabitResponse;
import com.habittracker.app.habit.data.dto.response.HabitResponse;
import com.habittracker.app.habit.svc.iface.HabitSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HabitController {
    private final HabitSvc habitSvc;

    @PostMapping("/habits")
    public CreateHabitResponse createHabit(
            @RequestBody CreateHabitRequest request) {
        return habitSvc.createhabit(request);
    }


    @GetMapping("/habits")
    public List<HabitResponse> fetchHabits() {
        return habitSvc.fetchHabits();
    }

    @GetMapping("/habits/{id}")
    public HabitResponse getHabit(@PathVariable String id) {
        return habitSvc.getHabit(id);
    }

    @PutMapping("/habits/{id}")
    public CreateHabitResponse updateHabit(@RequestBody UpdateHabitRequest request,
                                           @PathVariable String id) {
        return habitSvc.updateHabit(request, id);
    }

    @DeleteMapping("/habits/{id}")
    public String deleteHabit(@PathVariable String id) {
        return habitSvc.deleteHabit(id);
    }

}
