package com.habittracker.app.user.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum Role {
    SUPER_ADMIN, ADMIN, USER;

    private static final List<Role> roles =
            List.of(Role.values());
}
