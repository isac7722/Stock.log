package com.code2am.stocklog.domain.stocks.service;

import com.code2am.stocklog.domain.stocks.models.entity.Stocks;
import com.code2am.stocklog.domain.stocks.repository.StocksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class StocksService {
    private final String SERVICE_KEY = "Bt%2BhVFL95%2FMEthp5iyplyQGh16yHgcF65moL7eFYRF%2Bqc1rnE8ANo4qPuY%2B2C2qBCQL090e0I%2Bf5GE9IdYs%2BEw%3D%3D";

    @Autowired
    private  StocksRepository stocksRepository;

    public void saveStocks() throws JsonProcessingException {
        // API 호출
        String url = "http://apis.data.go.kr/1160100/service/GetKrxListedInfoService/getItemInfo?format=json&basDt=20240227&serviceKey=" + SERVICE_KEY;
        String response = "";

        try {
            // HTTP GET 요청을 통해 API 응답 받기
            response = new RestTemplate().getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            // API 호출 에러 처리
            System.out.println("API 호출 에러: " + e.getMessage());
            return;
        }

        // 데이터 파싱
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(response, Map.class);

        List<Map<String, Object>> stockList = (List<Map<String, Object>>) data.get("data");

        // DB에 저장
        for (Map<String, Object> stockData : stockList) {
            Stocks stock = new Stocks();

            stock.setStockCode((String) stockData.get("isinCode"));
            stock.setStockName((String) stockData.get("stockName"));

            stocksRepository.save(stock);
        }
    }

}
