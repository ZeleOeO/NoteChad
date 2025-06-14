package com.zele.notechad.service.impl;


import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.entities.Author;
import com.zele.notechad.entities.Note;
import com.zele.notechad.exception.author.AuthorNotFoundException;
import com.zele.notechad.exception.author.AuthorUnauthorizedException;
import com.zele.notechad.exception.note.NoteNotFoundException;
import com.zele.notechad.mapper.NoteMapper;
import com.zele.notechad.repository.AuthorRepository;
import com.zele.notechad.repository.NoteRepository;
import com.zele.notechad.security.JwtService;
import com.zele.notechad.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final JwtService jwtService;
    private final AuthorRepository authorRepository;

    @Override
    public ApiResponse<NoteViewDTO> getNotesByAuthor(String author) {
        var note = noteRepository.findByAuthorUserName(author).orElse(null);
        validateNote(note);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    @Override
    public ApiResponse<NoteViewDTO> createNewNote(String title, HttpServletRequest request) {
        Note note = new Note();
        note.setTitle(title);
        note.setAuthor(getAuthorFromToken(request));
        note.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        noteRepository.save(note);
        return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    @Override
    public ApiResponse<NoteViewDTO> writeNote(String content, Long noteId, HttpServletRequest request) {
        Note note = validateAuthor(noteId, request);
        note.setContent(content);
        note.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        noteRepository.save(note);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    @Override
    public ApiResponse<String> deleteNote(Long noteId, HttpServletRequest request) {
        Note note = validateAuthor(noteId, request);
        noteRepository.delete(note);
        return new ApiResponse<>(HttpStatus.OK.value(), "Note Deleted", HttpStatus.OK.getReasonPhrase());
    }

    // Helper Methods
    private Author getAuthorFromToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        authToken = authToken.substring(7);
        String authorName = jwtService.getUsernameFromToken(authToken);
        var author = authorRepository.findByUserName(authorName).orElse(null);
        if (author == null) throw new AuthorNotFoundException("Author Not Found");
        return author;
    }

    private void validateNote(Note note) {
        if (note == null) {
            log.error("Note is null");
            throw new NoteNotFoundException("Note Not Found");
        }
    }

    private Note validateAuthor(Long noteId, HttpServletRequest request) {
        Note note = noteRepository.findById(noteId).orElse(null);
        Author author = getAuthorFromToken(request);
        validateNote(note);
        if (!note.getAuthor().getUserName().equals(author.getUserName()))
            throw new AuthorUnauthorizedException("Author Not Authorized to interact with this note");
        return note;
    }
}
