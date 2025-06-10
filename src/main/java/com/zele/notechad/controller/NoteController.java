package com.zele.notechad.controller;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{author}")
    public ApiResponse<NoteViewDTO> getNoteByAuthor(@PathVariable String author) {
        return noteService.getNotesByAuthor(author);
    }
}
