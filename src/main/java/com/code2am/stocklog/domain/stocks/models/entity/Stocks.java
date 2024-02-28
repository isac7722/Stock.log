package com.code2am.stocklog.domain.stocks.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_STOCKS")
public class Stocks {

    @Id
    @Column(name = "STOCK_CODE")
    @GeneratedValue
    private String stockCode;

    @Column(name = "STOCK_NAME")
    private String stockName;
}
