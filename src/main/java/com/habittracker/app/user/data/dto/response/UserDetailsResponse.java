package com.habittracker.app.user.data.dto.response;

import com.habittracker.app.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponse {
    private String id;
    private String name;
    private String emailId;
    private String phoneNumber;
    private Instant createdAt;
    private Instant updatedAt;
    private Role role;
}
