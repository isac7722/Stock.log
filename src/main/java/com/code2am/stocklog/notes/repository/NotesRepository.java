package com.code2am.stocklog.notes.repository;

import com.code2am.stocklog.notes.models.dto.NotesDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository {
    void insertNoteByJournalId(NotesDTO notesDTO);
}
