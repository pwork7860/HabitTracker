package com.habittracker.app.habit.repo.iface;

import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.habit.data.models.Habit;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface HabitRepo extends MongoRepository<Habit, String> {

    Optional<Habit> findByUserIdAndNameAndStatus(String userId, String name, Status status);

    List<Habit> findByUserIdAndStatus(String userId, Status status);

    Optional<Habit> findByIdAndUserIdAndStatus(String id, String userId, Status status);
}
