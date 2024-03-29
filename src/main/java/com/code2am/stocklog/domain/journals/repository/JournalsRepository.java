package com.code2am.stocklog.domain.journals.repository;

import com.code2am.stocklog.domain.journals.model.entitiy.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalsRepository extends JpaRepository<Journal, Integer> {
}
