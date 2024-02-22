package com.code2am.stocklog.domain.strategies.controller;

import com.code2am.stocklog.domain.strategies.models.dto.StrategiesDTO;
import com.code2am.stocklog.domain.strategies.service.StrategiesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/strategies")
@Tag(name = "매매전략 관리 API", description = "매매 전략을 관리하는 API")
public class StrategiesController {

    @Autowired
    private StrategiesService strategiesService;

    @PostMapping
    public ResponseEntity createStrategy(@RequestBody StrategiesDTO strategy){

        if(Objects.isNull(strategy)){
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }

        strategiesService.createStrategy(strategy);

        return ResponseEntity.ok("정상적으로 등록되었습니다.");
    }

}
