package com.zele.notechad.exception;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.AuthorViewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
