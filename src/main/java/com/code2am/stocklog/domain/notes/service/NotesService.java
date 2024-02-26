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

    /**
     * 매매일지의 id 값을 받아 매매노트를 생성하는 메소드
     * */
    public Notes createNoteByJournalId(NotesDTO notesDTO) {

        // journalId를 통해 매매일지가 존재하는지 확인하는 검사 필요

        // DTO를 앤티티에 담아 JPA를 통해 등록
        Notes newNote = new Notes();
        newNote.setNoteContents(notesDTO.getNoteContents());
        newNote.setNoteDate(LocalDateTime.now());
        newNote.setNoteStatus("Y");
        newNote.setJournalId(notesDTO.getJournalId());
        notesRepository.save(newNote);

        return newNote;
    }


    /**
     * 매매일지의 id 값을 받아 그에 해당하는 매매노트를 전부 조회하는 메소드
     * */
    public List<NotesVo> readNotesByJournalId(Integer journalId) {

        // 넘어온 매매일지의 id가 정확한 값인지 확인
        if(journalId <= 0){
            System.out.println("존재할 수 없는 매매일지");
            return null;
        }

        // 매매노트를 리스트 형식으로 받아옴, 조회의 경우 mybatis를 이용한다.
        List<NotesVo> result = notesDAO.readNotesByJournalId(journalId);

        if(Objects.isNull(result)){
            return null;
        }

        return result;
    }


    /**
     * 매매일지를 삭제하는 메소드
     * */
    public void deleteNoteByNoteId(NotesDTO notesDTO) {

        // 실제로 삭제하는 것이 아니라 단지 상태값을 N으로 만드는 것
        // DTO를 앤티티에 담아 넘긴다.
        Notes deleteNote = new Notes();
        deleteNote.setNoteId(notesDTO.getNoteId());
        deleteNote.setNoteContents(notesDTO.getNoteContents());
        deleteNote.setNoteDate(notesDTO.getNoteDate());
        deleteNote.setNoteStatus("N");
        deleteNote.setJournalId(notesDTO.getJournalId());
        notesRepository.save(deleteNote);
    }
}
