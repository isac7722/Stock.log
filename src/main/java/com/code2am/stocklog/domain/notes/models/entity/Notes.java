package com.code2am.stocklog.domain.notes.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBL_NOTES")
public class Notes {

    @Id
    @Column(name = "NOTE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteId;

    @Column(name = "NOTE_CONTENTS")
    private String noteContents;

    @Column(name = "NOTE_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime noteDate;

    @Column(name = "NOTE_STATUS")
    private String noteStatus;

    @Column(name = "JOURNAL_ID")
    private Integer journalId;
}
