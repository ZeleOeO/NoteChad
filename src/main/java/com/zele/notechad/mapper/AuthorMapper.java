package com.zele.notechad.mapper;

import com.zele.notechad.dtos.author.AuthorCreateRequest;
import com.zele.notechad.dtos.author.AuthorViewDTO;
import com.zele.notechad.entities.Author;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorMapper {
    public AuthorViewDTO toAuthorViewDTO(Author author) {
        AuthorViewDTO authorViewDTO = new AuthorViewDTO();
        authorViewDTO.setId(author.getId());
        authorViewDTO.setDisplayName(author.getDisplayName());
        authorViewDTO.setUserName(author.getUserName());
        return authorViewDTO;
    }

    public Author createRequestToAuthor(AuthorCreateRequest createRequest) {
        Author author = new Author();
        author.setUserName(createRequest.getUsername());
        author.setPassword(new BCryptPasswordEncoder(12).encode(createRequest.getPassword()));
        author.setDisplayName(createRequest.getDisplayName());
        return author;
    }
}
