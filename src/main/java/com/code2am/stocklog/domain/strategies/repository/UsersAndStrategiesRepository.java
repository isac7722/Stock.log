package com.code2am.stocklog.domain.strategies.repository;

import com.code2am.stocklog.domain.strategies.models.entity.UsersAndStrategies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAndStrategiesRepository extends JpaRepository<UsersAndStrategies, Integer> {

}
