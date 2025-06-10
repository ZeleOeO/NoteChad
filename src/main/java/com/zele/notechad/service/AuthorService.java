package com.zele.notechad.service;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.author.AuthorCreateRequest;
import com.zele.notechad.dtos.author.AuthorViewDTO;
import com.zele.notechad.entities.Author;
import com.zele.notechad.exception.author.AuthorAlreadyExistsException;
import com.zele.notechad.exception.author.AuthorNotFoundException;
import com.zele.notechad.exception.author.PasswordMatchException;
import com.zele.notechad.mapper.AuthorMapper;
import com.zele.notechad.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public ApiResponse<List<AuthorViewDTO>> getAllAuthors() {
        List<AuthorViewDTO> authors = authorRepository.findAll()
                .stream()
                .map(authorMapper::toAuthorViewDTO)
                .toList();
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), authors);
    }

    public ApiResponse<AuthorViewDTO> getAuthorById(Long id) {
        var author = authorRepository.findById(id).orElse(null);
        validateAuthor(author);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), authorMapper.toAuthorViewDTO(author));
    }

    public ApiResponse<AuthorViewDTO> createAuthor(AuthorCreateRequest createRequest) {
        Author author = authorMapper.createRequestToAuthor(createRequest);
        if (!createRequest.getPassword().equals(createRequest.getConfirmPassword())) {
            log.error("Passwords do not match");
            throw new PasswordMatchException("Passwords do not match");
        }
        if (authorRepository.existsByUserName(author.getUserName())) {
            log.error("Author already exists");
            throw new AuthorAlreadyExistsException("Author already exists");
        }
        authorRepository.save(author);
        log.info("Created author: {}", author);
        return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), authorMapper.toAuthorViewDTO(author));
    }

    // Helper Methods
    private void validateAuthor(Author author) {
        if (author == null) {
            log.error("Author is null");
            throw new AuthorNotFoundException("Author not found");
        }
    }

}
