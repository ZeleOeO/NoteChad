package com.zele.notechad.repository;

import com.zele.notechad.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUserName(String name);

    boolean existsByUserName(String userName);
}
