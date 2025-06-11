package com.zele.notechad.security;

import com.zele.notechad.exception.author.AuthorNotFoundException;
import com.zele.notechad.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var author = authorRepository.findByUserName(username).orElse(null);
        if (author == null) throw new AuthorNotFoundException(username);
        return new User(author.getUserName(), author.getPassword(), Collections.emptyList());
    }
}
