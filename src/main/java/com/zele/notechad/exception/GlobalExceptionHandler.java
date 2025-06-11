package com.zele.notechad.exception;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.exception.author.AuthorAlreadyExistsException;
import com.zele.notechad.exception.author.AuthorNotFoundException;
import com.zele.notechad.exception.author.PasswordMatchException;
import com.zele.notechad.exception.note.NoteNotFoundException;
import jakarta.servlet.ServletException;
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

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoteNotFoundException(NoteNotFoundException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(PasswordMatchException.class)
    public ResponseEntity<ApiResponse<Object>> handlePasswordMatchException(PasswordMatchException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthorAlreadyExistsException(AuthorAlreadyExistsException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(GenericException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(AuthorAuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthorAuthenticationException(AuthorAuthenticationException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ApiResponse<Object>> handleServletException(ServletException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
