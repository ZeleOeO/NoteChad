package com.zele.notechad.mapper;

import com.zele.notechad.dtos.AuthorViewDTO;
import com.zele.notechad.entities.Author;
import lombok.AllArgsConstructor;
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
}
