package com.habittracker.app.habit.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "habit_completions")
@CompoundIndex(
        name = "idx_habit_completed_date",
        def = "{'habitId': 1, 'completedDate': 1}",
        unique = true)
@CompoundIndex(
        name = "idx_user_completed_date",
        def = "{'userId': 1, 'completedDate': 1}")
public class HabitCompletion {

    @Id
    private String id;
    private String habitId;
    private String userId;
    private Instant completedAt;
    private LocalDate completedDate;
    private Instant createdAt;

}
