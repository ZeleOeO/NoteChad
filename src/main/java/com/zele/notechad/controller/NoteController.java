package com.zele.notechad.controller;

import com.zele.notechad.dtos.ApiResponse;
import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{author}")
    public ApiResponse<NoteViewDTO> getNoteByAuthor(@PathVariable String author) {
        return noteService.getNotesByAuthor(author);
    }

    @PostMapping("/new-note")
    public ApiResponse<NoteViewDTO> createNote(@RequestBody String title, HttpServletRequest request) {
        return noteService.createNewNote(title, request);
    }

    @PostMapping("/update-note/{id}")
    public ApiResponse<NoteViewDTO> updateNote(@PathVariable Long id, @RequestBody String content, HttpServletRequest request) {
        return noteService.writeNote(content, id, request);
    }

    @DeleteMapping("/delete-note/{id}")
    public ApiResponse<String> deleteNote(@PathVariable Long id, HttpServletRequest request) {
        return noteService.deleteNote(id, request);
    }
}
