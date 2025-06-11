package com.zele.notechad.exception.author;

public class AuthorUnauthorizedException extends RuntimeException {
    public AuthorUnauthorizedException(String message) {
        super(message);
    }
}
