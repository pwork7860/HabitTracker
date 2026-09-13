package com.habittracker.app.commons.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum Status {
   ACTIVE, DELETED, INACTIVE, SUCCESS, FAILED, ERROR, COMPLETED;

    public static final List<Status> statuse =
            List.of(Status.values());
}
