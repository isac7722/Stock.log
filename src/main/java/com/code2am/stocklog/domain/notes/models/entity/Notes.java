package com.code2am.stocklog.domain.notes.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
    private Date noteDate;

    @Column(name = "NOTE_STATUS")
    private String noteStatus;

//    @JoinColumn(name = "JOURNAL_ID")
//    @ManyToOne
//    private Journal journal;
}
