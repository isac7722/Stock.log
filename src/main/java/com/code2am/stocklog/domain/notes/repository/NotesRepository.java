package com.code2am.stocklog.domain.notes.repository;

import com.code2am.stocklog.domain.notes.models.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
