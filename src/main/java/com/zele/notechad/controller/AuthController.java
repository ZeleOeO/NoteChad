package com.zele.notechad.controller;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.author.AuthorCreateRequest;
import com.zele.notechad.dtos.author.AuthorLoginRequest;
import com.zele.notechad.dtos.author.AuthorViewDTO;
import com.zele.notechad.service.AuthService;
import com.zele.notechad.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthorService authorService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<AuthorViewDTO> addAuthor(@RequestBody AuthorCreateRequest createRequest) {
        return authorService.createAuthor(createRequest);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody AuthorLoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
