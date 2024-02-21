package com.code2am.stocklog.notes.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotesDTO {

    private Integer noteId;

    private String noteContents;

    private Date noteDate;

    private String noteStatus;

    private Integer journalId;
}
