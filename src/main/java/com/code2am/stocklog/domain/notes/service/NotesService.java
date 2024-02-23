package com.code2am.stocklog.domain.notes.service;

import com.code2am.stocklog.domain.notes.dao.NotesDAO;
import com.code2am.stocklog.domain.notes.models.entity.Notes;
import com.code2am.stocklog.domain.notes.models.vo.NotesVo;
import com.code2am.stocklog.domain.notes.models.dto.NotesDTO;
import com.code2am.stocklog.domain.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesDAO notesDAO;

    public Notes createNoteByJournalId(NotesDTO notesDTO) {

        // journalId를 통해 매매일지가 존재하는지 확인하는 검사 필요

        Notes newNote = new Notes();
        newNote.setNoteContents(notesDTO.getNoteContents());
        newNote.setNoteDate(LocalDateTime.now());
        newNote.setNoteStatus("Y");
        newNote.setJournalId(notesDTO.getJournalId());
        notesRepository.save(newNote);

        return newNote;
    }


    public List<NotesVo> readNotesByJournalId(Integer journalId) {

        if(journalId <= 0){
            System.out.println("존재할 수 없는 매매일지");
            return null;
        }

        List<NotesVo> result = notesDAO.readNotesByJournalId(journalId);

        if(Objects.isNull(result)){
            return null;
        }

        return result;
    }


    public void deleteNoteByNoteId(NotesDTO notesDTO) {

        Notes deleteNote = new Notes();
        deleteNote.setNoteId(notesDTO.getNoteId());
        deleteNote.setNoteContents(notesDTO.getNoteContents());
        deleteNote.setNoteDate(notesDTO.getNoteDate());
        deleteNote.setNoteStatus("N");
        deleteNote.setJournalId(notesDTO.getJournalId());
        notesRepository.save(deleteNote);
    }
}
