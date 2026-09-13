package com.habittracker.app.habit.data.models;

import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.habit.enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "habits")
@CompoundIndex(name = "idx_user_created_at_frequency",
        def = "{userId:1, createdAt:-1, frequency:1}")
@CompoundIndex(
        name = "idx_user_status_createdAt",
        def = "{'userId': 1, 'status': 1, createdAt:-1}"
)
public class Habit {

    @Id
    private String id;
    private String name;
    private Frequency frequency;
    private String userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Status status;
}
