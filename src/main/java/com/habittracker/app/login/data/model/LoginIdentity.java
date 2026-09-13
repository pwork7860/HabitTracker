package com.habittracker.app.login.data.model;

import com.habittracker.app.login.enums.LoginSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "loginidentities")
@CompoundIndex(
        name = "uk_login_source_source_id",
        def = "{'loginSource': 1, 'loginSourceId': 1}",
        unique = true
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginIdentity {

    @Id
    private String id;

    @Indexed(name = "idx_user_id")
    private String userId;

    private LoginSource loginSource;

    private String loginSourceId;

    private Instant createdAt;

    private Instant updatedAt;
}
