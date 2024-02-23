package com.code2am.stocklog.domain.journals.model.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_TRADES")
@Data
public class Trade {

    // 매매기록 pk
    @Id
    @Column(name = "TRADE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    // 연관련 매매일지의 fk
    @JoinColumn(name = "JOURNAL_ID")
    private Integer journalId;

    // 현대 매매일지가 매수인지 매도인지 파악
    // buy || sell
    @Column(name = "STATUS")
    private String status;

    // 매매 날짜 - 당일치기 매매도 있을 수 있기 때문에 시간까지 나오는 timestamp으로 받음
    @Column(name = "TRADED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime tradeDate;

    // 매매 가격
    @Column(name = "PRICE")
    private Integer price;

    // 매매 물량
    @Column(name = "QUANTITY")
    private Integer quantity;

    // 매매기록의 활성화 상태
    @Column(name = "ACTIVATE_STATUS")
    private String activateStatus;



}
