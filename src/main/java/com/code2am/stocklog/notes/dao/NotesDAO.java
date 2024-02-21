package com.code2am.stocklog.notes.dao;

import com.code2am.stocklog.notes.models.vo.NotesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotesDAO {
    List<NotesVo> readNotes();
}
