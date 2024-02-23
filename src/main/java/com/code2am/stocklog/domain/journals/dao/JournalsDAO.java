package com.code2am.stocklog.domain.journals.dao;

import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JournalsDAO {

    List<JournalDTO> readJournalsByUserId(Integer userId);
}
