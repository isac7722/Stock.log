package com.code2am.stocklog.domain.notes.dao;

import com.code2am.stocklog.domain.notes.models.vo.NotesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotesDAO {
    List<NotesVo> readNotesByJournalId(Integer journalId);

}
