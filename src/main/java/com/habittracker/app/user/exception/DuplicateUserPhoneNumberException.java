package com.habittracker.app.user.exception;

public class DuplicateUserPhoneNumberException extends RuntimeException{
    public DuplicateUserPhoneNumberException() {
        super("number already exists!");
    }
}
