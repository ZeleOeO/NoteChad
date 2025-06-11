package com.zele.notechad.service;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.entities.Note;
import com.zele.notechad.exception.author.AuthorNotFoundException;
import com.zele.notechad.exception.note.NoteNotFoundException;
import com.zele.notechad.mapper.NoteMapper;
import com.zele.notechad.repository.AuthorRepository;
import com.zele.notechad.repository.NoteRepository;
import com.zele.notechad.security.JwtService;
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
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final JwtService jwtService;
    private final AuthorRepository authorRepository;

    public ApiResponse<NoteViewDTO> getNotesByAuthor(String author) {
        var note = noteRepository.findByAuthorUserName(author).orElse(null);
        if (!validateNote(note)) throw new NoteNotFoundException("Note Not Found");
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    public ApiResponse<NoteViewDTO> createNewNote(String title, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        authToken = authToken.substring(7);
        String authorName = jwtService.getUsernameFromToken(authToken);
        var author = authorRepository.findByUserName(authorName).orElse(null);
        if (author == null) throw new AuthorNotFoundException("Author Not Found");
        Note note = new Note();
        note.setTitle(title);
        note.setAuthor(author);
        note.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        noteRepository.save(note);
        return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    private boolean validateNote(Note note) {
        if (note == null) {
            log.error("Note is null");
            return false;
        }
        return true;
    }
}
