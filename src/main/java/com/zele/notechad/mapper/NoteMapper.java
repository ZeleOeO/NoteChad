package com.zele.notechad.mapper;

import com.zele.notechad.dtos.note.NoteViewDTO;
import com.zele.notechad.entities.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoteMapper {
    public NoteViewDTO toNoteViewDTO(Note note) {
        NoteViewDTO noteViewDTO = new NoteViewDTO();
        noteViewDTO.setId(note.getId());
        noteViewDTO.setTitle(note.getTitle());
        noteViewDTO.setContent(note.getContent());
        noteViewDTO.setCreatedAt(note.getCreatedAt());
        noteViewDTO.setUpdatedAt(note.getUpdatedAt());
        noteViewDTO.setAuthor(note.getAuthor().getUserName());
        return noteViewDTO;
    }
}
