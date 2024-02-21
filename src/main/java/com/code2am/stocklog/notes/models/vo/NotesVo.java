package com.code2am.stocklog.notes.models.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NotesVo {

    private Integer noteId;

    private String noteContents;

    private Date noteDate;

}
