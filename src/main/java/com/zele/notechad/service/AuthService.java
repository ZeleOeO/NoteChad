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

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ApiResponse<String> login(AuthorLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (!authentication.isAuthenticated()) throw new AuthorAuthenticationException("Authentication failed");
        return new ApiResponse<>(HttpStatus.OK.value(), "User Authenticated", jwtService.generateToken(request.getUsername()));
    }
}