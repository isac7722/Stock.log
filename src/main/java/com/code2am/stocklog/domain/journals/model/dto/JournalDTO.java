package com.code2am.stocklog.domain.journals.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class JournalDTO {

    // 종목 코드
    private Integer stockCode;

    // 매매전략 pk
    private Integer strategyId;

    // 매매기록
    List<TradeDTO> trades;

}
