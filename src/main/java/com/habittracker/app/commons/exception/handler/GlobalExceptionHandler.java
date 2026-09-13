package com.habittracker.app.commons.exception.handler;

import com.habittracker.app.commons.dto.response.ErrorResponse;
import com.habittracker.app.commons.utils.ErrorResponseAdapter;
import com.habittracker.app.habit.eception.DuplicateHabitNameException;
import com.habittracker.app.habit.eception.HabitNotFoundException;
import com.habittracker.app.user.exception.DuplicateUserEmailException;
import com.habittracker.app.user.exception.DuplicateUserPhoneNumberException;
import com.habittracker.app.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> validatonExceptionHanlder (
            MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.BAD_REQUEST)
                        .errors(errors)
                        .timestamp(System.currentTimeMillis())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateNameExceptionHandler (
            DuplicateHabitNameException duplicateHabitNameException) {
        return ErrorResponseAdapter
                .buildErrorResponse(duplicateHabitNameException, HttpStatus.CONFLICT);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> habitNotFoundExceptionHandler (
            HabitNotFoundException habitNotFoundException) {
        return ErrorResponseAdapter
                .buildErrorResponse(habitNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler (
            UserNotFoundException userNotFoundException) {
        return ErrorResponseAdapter
                .buildErrorResponse(userNotFoundException, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateUserEmailExceptionHandler (
            DuplicateUserEmailException duplicateUserEmailException) {
        return ErrorResponseAdapter
                .buildErrorResponse(duplicateUserEmailException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> duplicateUserNumberExceptionHandler (
            DuplicateUserPhoneNumberException duplicateUserPhoneNumberException) {
        return ErrorResponseAdapter
                .buildErrorResponse(duplicateUserPhoneNumberException, HttpStatus.CONFLICT);
    }

}
