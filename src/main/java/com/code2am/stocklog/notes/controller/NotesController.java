package com.code2am.stocklog.notes.controller;

import com.code2am.stocklog.notes.models.dto.NotesDTO;
import com.code2am.stocklog.notes.models.entity.Notes;
import com.code2am.stocklog.notes.models.vo.NotesVo;
import com.code2am.stocklog.notes.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/notes")
@Tag(name = "매매노트 API", description = "매매 노트를 관리하는 API")
public class NotesController {

    @Autowired
    public NotesService notesService;


    @Operation(
            summary = "매매노트 등록",
            description = "신규 매매노트를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매노트를 성공적으로 등록함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "notesDTO", description = "매매일지에 신규로 등록할 매매노트")
    @PostMapping
    public ResponseEntity createNoteByJournalId(@RequestBody NotesDTO notesDTO){
        // 매매일지에서 이용자의 요청을 받아 해당 일지에 노트를 작성한다.

        // 요청값이 없는지 확인
        if(Objects.isNull(notesDTO)){
            return ResponseEntity.status(404).body("입력값이 없습니다.");
        }

        Notes note = notesService.createNoteByJournalId(notesDTO);


        if(Objects.isNull(note)){
            return ResponseEntity.status(500).body("서버의 통신이 원할치 못합니다.");
        }

        System.out.println(note);
        return ResponseEntity.ok("등록에 성공하였습니다.");
    }

    @GetMapping
    public List<NotesVo> readNotes(){
        // xml 테스트
        return notesService.readNotes();
    }
}