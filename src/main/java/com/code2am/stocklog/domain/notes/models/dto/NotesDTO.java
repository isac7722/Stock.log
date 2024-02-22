package com.code2am.stocklog.domain.notes.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotesDTO {
    // 매매노트 PK
    private Integer noteId;
    // 매매노트 내용
    private String noteContents;
    // 매매노트 등록 날짜
    private LocalDateTime noteDate;
    // 매매노트 상태
    private String noteStatus;
    // 매매일지 FK
    private Integer journalId;
}
