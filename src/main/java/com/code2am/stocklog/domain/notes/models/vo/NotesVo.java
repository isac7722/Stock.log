package com.code2am.stocklog.domain.notes.models.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotesVo {

    private Integer noteId;

    private String noteContents;

    private LocalDateTime noteDate;

}