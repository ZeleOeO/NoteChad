package com.zele.notechad.service;

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
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface NoteService {
    ApiResponse<NoteViewDTO> getNotesByAuthor(String author);
    ApiResponse<NoteViewDTO> createNewNote(String title, HttpServletRequest request);
    ApiResponse<NoteViewDTO> writeNote(String content, Long noteId, HttpServletRequest request);
    ApiResponse<String> deleteNote(Long noteId, HttpServletRequest request);
}
