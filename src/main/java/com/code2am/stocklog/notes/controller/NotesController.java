package com.code2am.stocklog.notes.controller;

import com.code2am.stocklog.notes.models.dto.NotesDTO;
import com.code2am.stocklog.notes.models.vo.NotesVo;
import com.code2am.stocklog.notes.service.NotesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/notes")
@Tag(name = "매매노트 API", description = "매매 노트를 관리하는 API")
public class NotesController {

    @Autowired
    public NotesService notesService;

    @GetMapping("/{userId}")
    public NotesVo searchNotesByUserId(@PathVariable int userId){
        // 유저의 id 값과 매매일지의 id 값을 받아 그에 해당하는


        return null;
    }

    @PostMapping
    public ResponseEntity insertNoteByJournalId(@RequestBody NotesDTO notesDTO){
        // 매매일지에서 이용자의 요청을 받아 해당 일지에 노트를 작성한다.

        // 요청값이 없는지 확인
        if(Objects.isNull(notesDTO)){
            return ResponseEntity.status(404).build();
        }

        notesService.insertNoteByJournalId(notesDTO);

        return ResponseEntity.ok("정상 등록되었습니다.");
    }
}
