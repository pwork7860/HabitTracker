package com.habittracker.app.user.utils;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.habittracker.app.user.data.dto.requests.CreateUserReq;
import com.habittracker.app.user.data.dto.response.CreateUserRes;
import com.habittracker.app.user.data.dto.response.UpdateUserRes;
import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.data.model.User;
import com.habittracker.app.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@RequiredArgsConstructor
public class UserAdapter {

    private final PasswordEncoder passwordEncoder;

    public static User getUser(CreateUserReq req) {
        return User.builder()
                .name(req.getName())
                .emailId(req.getEmailId())
                .phoneNumber(req.getPhoneNumber())
                .password(req.getPassword())
                .role(Role.USER)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .timeZone(req.getTimeZone() == null
                        ? "Asia/Kolkata" : req.getTimeZone())
                .build();
    }

    public static CreateUserRes getCreateUserResponse(User user) {
        return CreateUserRes.builder()
                .id(user.getId())
                .name(user.getName())
                .emailId(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UpdateUserRes getUpdateUserResponse(User user) {
        return UpdateUserRes.builder()
                .id(user.getId())
                .name(user.getName())
                .emailId(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserDetailsResponse getUserDetailsResponse(User user) {
        return UserDetailsResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .emailId(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User getUserthroughPayload(GoogleIdToken.Payload payload) {
        return User.builder()
                .name((String) payload.get("name"))
                .emailId(payload.getEmail())
                .updatedAt(Instant.now())
                .role(Role.USER)
                .build();
    }
}
