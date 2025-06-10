package com.zele.notechad.controller;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.author.AuthorViewDTO;
import com.zele.notechad.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/all")
    public ApiResponse<List<AuthorViewDTO>> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ApiResponse<AuthorViewDTO> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }
}
