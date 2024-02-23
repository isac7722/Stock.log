package com.code2am.stocklog.domain.journals.dao;

import com.code2am.stocklog.domain.journals.model.dto.TradeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradesDAO {

    List<TradeDTO> readTradesByJournalId(Integer journalId);
}
