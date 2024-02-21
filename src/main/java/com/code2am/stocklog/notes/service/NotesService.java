package com.code2am.stocklog.notes.service;

import com.code2am.stocklog.notes.mapper.NotesMapper;
import com.code2am.stocklog.notes.models.dto.NotesDTO;
import com.code2am.stocklog.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesMapper notesMapper;

    public void insertNoteByJournalId(NotesDTO notesDTO) {

        notesRepository.insertNoteByJournalId(notesDTO);
    }
}
