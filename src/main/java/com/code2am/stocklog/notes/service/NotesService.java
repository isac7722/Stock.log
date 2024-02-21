package com.code2am.stocklog.notes.service;

import com.code2am.stocklog.notes.dao.NotesDAO;
import com.code2am.stocklog.notes.models.dto.NotesDTO;
import com.code2am.stocklog.notes.models.entity.Notes;
import com.code2am.stocklog.notes.models.vo.NotesVo;
import com.code2am.stocklog.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesDAO notesDAO;

    public Notes createNoteByJournalId(NotesDTO notesDTO) {

        Notes newNote = new Notes();
        newNote.setNoteContents(notesDTO.getNoteContents());
        newNote.setNoteDate(notesDTO.getNoteDate());
        newNote.setNoteStatus("Y");
        notesRepository.save(newNote);

        return newNote;
    }

    public List<NotesVo> readNotes() {
        return notesDAO.readNotes();
    }
}
