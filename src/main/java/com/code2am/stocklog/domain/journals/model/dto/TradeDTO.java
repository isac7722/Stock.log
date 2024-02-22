package com.code2am.stocklog.domain.journals.model.dto;

import lombok.Data;

@Data
public class TradeDTO {

    private Integer tradeId;
    private Integer status;
    private Integer tradeDate;
    private Integer price;
    private Integer quantity;
    private String activateStatus;

}
