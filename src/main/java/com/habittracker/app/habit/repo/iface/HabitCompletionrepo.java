package com.habittracker.app.habit.repo.iface;

import com.habittracker.app.habit.data.models.HabitCompletion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitCompletionrepo extends MongoRepository<HabitCompletion, String> {
    List<HabitCompletion> findByUserIdAndCompletedDate(String userId, LocalDate completedDate);
}
