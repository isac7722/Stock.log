package com.code2am.stocklog.domain.strategies.dao;

import com.code2am.stocklog.domain.strategies.models.dto.StrategiesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface StrategiesDAO {
    List<StrategiesDTO> readStrategies();

    StrategiesDTO readStrategyByStrategyId(Integer strategyId);

    StrategiesDTO readStrategyByStrategyName(String strategyName);

    List<StrategiesDTO> readStrategiesByUserId(Integer userId);

    void deleteStrategyByStrategyIdAndUserId(@RequestParam("strategyId") Integer strategyId, @RequestParam("userId") Integer userId);
}
