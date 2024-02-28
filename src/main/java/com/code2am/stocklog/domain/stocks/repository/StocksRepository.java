package com.code2am.stocklog.domain.stocks.repository;

import com.code2am.stocklog.domain.stocks.models.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer> {
}
