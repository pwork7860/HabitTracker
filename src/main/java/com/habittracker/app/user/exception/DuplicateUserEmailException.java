package com.habittracker.app.user.exception;

public class DuplicateUserEmailException extends RuntimeException{
    public DuplicateUserEmailException() {
        super("Email already exists!");
    }
}
