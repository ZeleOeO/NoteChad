package com.zele.notechad.repository;

import com.zele.notechad.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByAuthorUserName(String authorUserName);
}
