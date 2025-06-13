package com.zele.notechad.service;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.author.AuthorLoginRequest;
import com.zele.notechad.exception.AuthorAuthenticationException;
import com.zele.notechad.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

public interface AuthService {
    ApiResponse<String> login(AuthorLoginRequest request);
}