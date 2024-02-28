package com.code2am.stocklog.domain.stocks.service;

import com.code2am.stocklog.domain.stocks.models.dto.StocksDTO;
import com.code2am.stocklog.domain.stocks.models.entity.Stocks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StocksService {
    private final WebClient webClient;
    private final String serviceKey = "Bt%2BhVFL95%2FMEthp5iyplyQGh16yHgcF65moL7eFYRF%2Bqc1rnE8ANo4qPuY%2B2C2qBCQL090e0I%2Bf5GE9IdYs%2BEw%3D%3D";

    public StocksService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://apis.data.go.kr/1160100/service/GetKrxListedInfoService/getItemInfo").build();
    }

    public Mono<StocksDTO> fetchDataFromExternalAPI() {
        return webClient.get()
                .uri("?resultType=json&serviceKey=" + serviceKey)
                .retrieve()
                .bodyToMono(String.class) // JSON 문자열로 응답을 받음
                .map(this::extractDesiredValues); // 원하는 값만 추출하여 StocksDTO 객체로 매핑
    }

    private StocksDTO extractDesiredValues(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            // JSON 노드에서 필요한 값을 추출하여 새로운 DTO 객체를 생성
            String stockName = rootNode.path("itmsNm").asText(); // stockName에 해당하는 값 추출
            String stockCode = rootNode.path("isinCd").asText(); // stockCode에 해당하는 값 추출
            StocksDTO stocksDTO = new StocksDTO();
            stocksDTO.setStocksName(stockName); // DTO에 설정
            stocksDTO.setStocksCode(stockCode); // DTO에 설정
            return stocksDTO;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
