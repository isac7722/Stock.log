package com.code2am.stocklog.domain.stocks.controller;


import com.code2am.stocklog.domain.stocks.service.StocksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @GetMapping
    public void getStocks() throws JsonProcessingException {
        // 서비스 로직 호출
        stocksService.saveStocks();
    }

}
