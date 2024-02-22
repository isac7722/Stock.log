package com.code2am.stocklog.domain.journals.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JournalDTO {

    // 매매일지 pk
    private Integer journalId;

    // 매매일지 오너
    private Integer userId;

    // 종목 코드
    private Integer stockCode;

    // 매매전략 fk
    private Integer strategyId;

    // 최종 거래일
    private LocalDateTime lastTradedDate;

    // 매수 평균가격
    private Integer buyPrice;

    // 매수 총 물량
    private Integer buyQty;

    // 매도 평균 가격
    private Integer sellPrice;

    // 매도 총 물량
    private Integer sellQty;

    // 수익금
    private Integer profit;

    // 매매일지 활성화 상태
    private String status;







    // 매매기록
    List<TradeDTO> trades;

}
