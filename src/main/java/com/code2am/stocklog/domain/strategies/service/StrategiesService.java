package com.code2am.stocklog.domain.strategies.service;

import com.code2am.stocklog.domain.strategies.dao.StrategiesDAO;
import com.code2am.stocklog.domain.strategies.models.dto.StrategiesDTO;
import com.code2am.stocklog.domain.strategies.models.entity.Strategies;
import com.code2am.stocklog.domain.strategies.repository.StrategiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategiesService {

    @Autowired
    private StrategiesRepository strategiesRepository;

    @Autowired
    private StrategiesDAO strategiesDAO;

    public void createStrategy(StrategiesDTO strategy) {

        // 동일한 매매전략이 있을 경우 행하지 않는다.

        Strategies newStrategy = new Strategies();
        newStrategy.setStrategyName(strategy.getStrategyName());
        newStrategy.setStrategyStatus("Y");

        strategiesRepository.save(newStrategy);
    }

    public List<StrategiesDTO> readStrategies() {
        return strategiesDAO.readStrategies();
    }
}
