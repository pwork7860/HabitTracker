package com.habittracker.app.login.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.habittracker.app.login.data.model.LoginIdentity;
import com.habittracker.app.login.enums.LoginSource;
import com.habittracker.app.user.data.model.User;
import com.habittracker.app.user.service.impl.UserDt;
import org.springframework.security.core.Authentication;
import java.time.Instant;

public class LoginAdapter {

    public static User getUserfromAuth(Authentication authentication) {
        UserDt userDt = (UserDt) authentication.getPrincipal();
        return User.builder()
                .id(userDt.getUserId())
                .name(authentication.getName())
                .role(userDt.getUseRole())
                .build();
    }

    public static LoginIdentity getloginIdentity(
            GoogleIdToken.Payload payload, String userId) {
        return LoginIdentity
                .builder()
                .loginSource(LoginSource.GOOGLE)
                .loginSourceId(payload.getSubject())
                .userId(userId)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
