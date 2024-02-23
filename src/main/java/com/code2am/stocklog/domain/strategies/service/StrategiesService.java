package com.code2am.stocklog.domain.strategies.service;

import com.code2am.stocklog.domain.strategies.dao.StrategiesDAO;
import com.code2am.stocklog.domain.strategies.models.dto.StrategiesDTO;
import com.code2am.stocklog.domain.strategies.models.entity.Strategies;
import com.code2am.stocklog.domain.strategies.repository.StrategiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StrategiesService {

    @Autowired
    private StrategiesRepository strategiesRepository;

    @Autowired
    private StrategiesDAO strategiesDAO;

    public String createStrategy(StrategiesDTO strategy) {

        // 동일한 이름의 매매전략이 있을 경우 행하지 않는다.
        StrategiesDTO find = readStrategyByStrategyName(strategy.getStrategyName());
        if(!Objects.isNull(find)){
            return "";
        }

        Strategies newStrategy = new Strategies();
        newStrategy.setStrategyName(strategy.getStrategyName());
        newStrategy.setStrategyStatus("Y");

        strategiesRepository.save(newStrategy);
        return "정상적으로 등록되었습니다.";
    }

    public List<StrategiesDTO> readStrategies() {
        return strategiesDAO.readStrategies();
    }

    public void deleteStrategyByStrategyId(StrategiesDTO strategy) {
        // 실제로는 삭제가 아닌 상태를 변경하는 로직

        StrategiesDTO find = readStrategyByStrategyId(strategy.getStrategyId());

        // 상태가 "Y"인 것 중 해당되는 것이 없다면 로직을 정지
        if(Objects.isNull(find)){
            return;
        }

        Strategies deleteStrategy = new Strategies();
        deleteStrategy.setStrategyId(strategy.getStrategyId());
        deleteStrategy.setStrategyName(strategy.getStrategyName());
        deleteStrategy.setStrategyStatus("N");

        strategiesRepository.save(deleteStrategy);
    }

    public StrategiesDTO readStrategyByStrategyId(Integer strategyId){
        return strategiesDAO.readStrategyByStrategyId(strategyId);
    }

    public StrategiesDTO readStrategyByStrategyName(String strategyName){
        return strategiesDAO.readStrategyByStrategyName(strategyName);
    }
}
