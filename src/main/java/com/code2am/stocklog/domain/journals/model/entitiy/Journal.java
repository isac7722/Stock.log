package com.code2am.stocklog.domain.journals.model.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_JOURNALS")
@Data
public class Journal {


    // 매매일지 pk
    @Id
    @Column(name = "JOURNAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer journalId;

    // 매매일지에 들어가는 종목의 코드 - fk
    @Column(name = "STOCK_CODE")
    private Integer stockCode;

    // 최종 거래일
    @Column(name = "LAST_TRADED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastTradedDate;

    // 매수 평균금가
    @Column(name = "BUY_PRICE")
    private Integer buyPrice;

    // 매수 총량
    @Column(name = "BUY_QTY")
    private Integer buyQty;

    // 매도 평균가
    @Column(name = "SELL_PRICE")
    private Integer sellPrice;

    // 매도 총량
    @Column(name = "SELL_QTY")
    private Integer sellQty;

    // 수익금
    @Column(name = "PROFIT")
    private Integer profit;

    // 현재 매매일지의 상태 - 삭제 여부를 나타냄
    @Column(name = "STATUS")
    private String status;

    // 현재 매매일지의 주인
    @Column(name = "USER_ID")
    private Integer userId;

    // 현재 매매일지에 관련되어 있는 매매전략
    @Column(name = "STRATEGY_ID")
    private Integer strategyId;

    /* 현재 매매일지와 연관이 있는 매매기록들 */
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "journalId")
    private List<Trade> trades = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "journalId")
    private List<Journal> journals = new ArrayList<>();





    /* 현재 매매일지와 연관이 있는 매매기록들 */
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "journalCode")
//    private List<Trade> trades = new ArrayList<>();


    /* ================================================================= */
    /* 매매일지와 관련 있는 Notes 추가 필요*/



}
