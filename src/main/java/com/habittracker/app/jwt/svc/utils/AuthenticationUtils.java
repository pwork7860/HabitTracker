package com.habittracker.app.jwt.svc.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

    public static String getUserIdFromAuth() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null && !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        return authentication.getName();
    }

}
