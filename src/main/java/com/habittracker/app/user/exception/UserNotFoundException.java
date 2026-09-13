package com.habittracker.app.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userId) {
        super("User not found with id: " + userId);
    }

}
