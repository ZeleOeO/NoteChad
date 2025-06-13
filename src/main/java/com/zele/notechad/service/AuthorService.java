package com.zele.notechad.service;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.author.AuthorCreateRequest;
import com.zele.notechad.dtos.author.AuthorViewDTO;

import java.util.List;

public interface AuthorService {
    ApiResponse<List<AuthorViewDTO>> getAllAuthors();

    ApiResponse<AuthorViewDTO> getAuthorById(Long id);

    ApiResponse<AuthorViewDTO> createAuthor(AuthorCreateRequest createRequest);
}