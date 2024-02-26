package com.code2am.stocklog.domain.strategies.service;

import com.code2am.stocklog.domain.strategies.dao.StrategiesDAO;
import com.code2am.stocklog.domain.strategies.models.dto.StrategiesDTO;
import com.code2am.stocklog.domain.strategies.models.entity.Strategies;
import com.code2am.stocklog.domain.strategies.models.entity.UsersAndStrategies;
import com.code2am.stocklog.domain.strategies.models.entity.UsersAndStrategiesId;
import com.code2am.stocklog.domain.strategies.repository.StrategiesRepository;
import com.code2am.stocklog.domain.strategies.repository.UsersAndStrategiesRepository;
import com.code2am.stocklog.domain.users.models.entity.Users;
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

    @Autowired
    private UsersAndStrategiesRepository usersAndStrategiesRepository;

    /**
     * 매매전략을 생성하는 메소드
     * */
    public String createStrategy(StrategiesDTO strategy) {

        Strategies newStrategy = new Strategies();
        UsersAndStrategies usersAndStrategies = new UsersAndStrategies(); // 복합키, 중간 테이블을 이용해 직접적인 관계를 만들지 않도록 한다.
        Users users = new Users();

        // 동일한 이름의 매매전략이 있을 경우 행하지 않는다.
        StrategiesDTO find = readStrategyByStrategyName(strategy.getStrategyName());
        if(!Objects.isNull(find)){

            // 해당 유저의 유저 정보를 엔티티에 담아야 한다.
            users.setUserId(2); // 임시, 변경 필수

            newStrategy.setStrategyId(find.getStrategyId());

            usersAndStrategies.setUsers(users);
            usersAndStrategies.setStrategies(newStrategy);
            usersAndStrategiesRepository.save(usersAndStrategies);

            newStrategy.setStrategyName(find.getStrategyName());
            newStrategy.setStrategyStatus("Y");
            strategiesRepository.save(newStrategy);
            return "등록되었습니다.";
        }

        // 유저 정보 임시, 변경 필수
        users.setUserId(1);

        newStrategy.setStrategyName(strategy.getStrategyName());
        newStrategy.setStrategyStatus("Y");

        usersAndStrategies.setUsers(users);
        usersAndStrategies.setStrategies(newStrategy);

        strategiesRepository.save(newStrategy);
        usersAndStrategiesRepository.save(usersAndStrategies);

        return "정상적으로 등록되었습니다.";
    }

    /**
     * 매매전략을 조회하는 메소드
     * */
    public List<StrategiesDTO> readStrategies() {
        return strategiesDAO.readStrategies();
    }

    /**
     * 매매전략을 삭제하는 메소드
     * */
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

    /**
     * 매매전략을 해당 id 값을 이용해 조회하는 메소드
     * */
    public StrategiesDTO readStrategyByStrategyId(Integer strategyId){
        return strategiesDAO.readStrategyByStrategyId(strategyId);
    }

    /**
     * 매매전략을 해당 이름으로 조회하는 메소드
     * */
    public StrategiesDTO readStrategyByStrategyName(String strategyName){
        return strategiesDAO.readStrategyByStrategyName(strategyName);
    }

    /**
     * 매매전략을 사용자의 id 값을 이용해 리스트로 반환하도록 조회하는 메소드
     * */
    public List<StrategiesDTO> readStrategiesByUserId(Integer userId) {
        return strategiesDAO.readStrategiesByUserId(userId);
    }

    /**
     * 매매전략을 작성한 사용자의 id 값과 자체 id 값을 이용해 매매전략을 삭제하는 메소드
     * */
    public void deleteStrategyByStrategyIdAndUserId(StrategiesDTO strategy, Integer userId) {

        Integer strategyId = strategy.getStrategyId();

        // 실제로는 데이터의 직접적인 삭제가 아니라 그 관계를 잘라내는 것이다.
        strategiesDAO.deleteStrategyByStrategyIdAndUserId(strategyId, userId);
    }
}
