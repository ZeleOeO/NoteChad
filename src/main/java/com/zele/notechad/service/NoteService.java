package com.zele.notechad.service;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.entities.Note;
import com.zele.notechad.exception.note.NoteNotFoundException;
import com.zele.notechad.mapper.NoteMapper;
import com.zele.notechad.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public ApiResponse<NoteViewDTO> getNotesByAuthor(String author) {
        var note = noteRepository.findByAuthorUserName(author).orElse(null);
        if (!validateNote(note)) throw new NoteNotFoundException("Note Not Found");
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), noteMapper.toNoteViewDTO(note));
    }

    private boolean validateNote(Note note) {
        if (note == null) {
            log.error("Note is null");
            return false;
        }
        return true;
    }
}
