package com.code2am.stocklog.domain.journals.repository;

import com.code2am.stocklog.domain.journals.model.entitiy.Trades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradesRepository extends JpaRepository<Trades, Integer> {

}
