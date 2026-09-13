package com.habittracker.app.commons.utils;

import com.habittracker.app.commons.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ErrorResponseAdapter {

    public static ResponseEntity<ErrorResponse> buildErrorResponse(
            RuntimeException exception, HttpStatus httpStatus) {
    ErrorResponse errorResponse = getErrorResponse(exception, httpStatus);
    return ResponseEntity.status(httpStatus)
            .body(errorResponse);

    }

    public static ErrorResponse getErrorResponse(RuntimeException exception, HttpStatus httpStatus) {
        return ErrorResponse
                .builder()
                .errors(List.of(exception.getMessage()))
                .message(exception.getMessage())
                .success(false)
                .status(httpStatus)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
