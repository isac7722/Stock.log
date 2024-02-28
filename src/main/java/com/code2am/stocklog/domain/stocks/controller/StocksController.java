package com.code2am.stocklog.domain.stocks.controller;


import com.code2am.stocklog.domain.stocks.models.dto.StocksDTO;
import com.code2am.stocklog.domain.stocks.service.StocksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StocksController {

        private final StocksService stocksService;

        public StocksController(StocksService stocksService) {
            this.stocksService = stocksService;
        }

        @GetMapping("/data")
        public Mono<StocksDTO> getExternalData() {
            return stocksService.fetchDataFromExternalAPI();
        }


}
