package com.habittracker.app.user.data.model;

import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.user.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String emailId;
    private String phoneNumber;
    private String profileImgUrl;
    private String password;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
    private String timeZone;
    private Status status;

}
