package com.code2am.stocklog.domain.journals.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeDTO {

    private Integer tradeId;
    private Integer journalId;
    private String status;
    private LocalDateTime tradeDate;
    private Integer price;
    private Integer quantity;
    private String activateStatus;

}
