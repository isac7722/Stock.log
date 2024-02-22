package com.code2am.stocklog.domain.notes.controller;

import com.code2am.stocklog.domain.notes.models.dto.NotesDTO;
import com.code2am.stocklog.domain.notes.models.entity.Notes;
import com.code2am.stocklog.domain.notes.models.vo.NotesVo;
import com.code2am.stocklog.domain.notes.service.NotesService;
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

    /**
     * 매매노트를 입력받는 메소드
     * @param notesDTO
     * @return ResponseEntity.ok */
    @Operation(
            summary = "매매노트 등록",
            description = "신규 매매노트를 등록합니다.",
            tags = {"POST"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매노트를 성공적으로 등록함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "notesDTO", description = "매매일지에 신규로 등록할 매매노트")
    @PostMapping
    public ResponseEntity createNoteByJournalId(@RequestBody NotesDTO notesDTO){
        // 매매일지에서 이용자의 요청을 받아 해당 일지에 노트를 작성한다. 매매일지의 키 값을 필수로 요구한다.

        // 요청값이 없는지 확인
        if(Objects.isNull(notesDTO)){
            return ResponseEntity.status(404).body("입력값이 없습니다.");
        }

        Notes note = notesService.createNoteByJournalId(notesDTO);

        // 요청이 원활히 도착했는지 확인
        if(Objects.isNull(note)){
            return ResponseEntity.status(500).body("서버의 통신이 원할치 못합니다.");
        }

        System.out.println(note);
        return ResponseEntity.ok("등록에 성공하였습니다.");
    }

    /**
     * 매매일지에 해당하는 매매노트를 조회하는 메소드
     * @param notesDTO
     * @return 매매일지에 해당되는 모든 매매노트
     * */
    @Operation(
            summary = "매매노트 조회",
            description = "매매일지의 PrimaryKey 값과 노트의 상태가 'Y'인 조건으로 매매노트를 조회합니다.",
            tags = {"GET"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매노트를 성공적으로 조회함."),
            @ApiResponse(responseCode = "404", description = "해당하는 매매일지가 존재하지 않음."),
            @ApiResponse(responseCode = "500", description = "서버가 원할히 동작하지 않거나 DB의 값이 존재하지 않음.")
    })
    @GetMapping
    public List<NotesVo> readNotesByJournalId(@RequestBody NotesDTO notesDTO){

        if(Objects.isNull(notesDTO)){
            return null;
        }

        Integer journalId = notesDTO.getJournalId();

        return notesService.readNotesByJournalId(journalId);
    }

    /**
     * 해당 매매노트를 삭제하는 메소드
     * @param notesDTO
     * @return 해당 매매노트를 삭제함
     * */
    @Operation(
            summary = "매매노트 삭제",
            description = "이미 존재하고 있는 매매노트를 삭제합니다.",
            tags = {"DELETE"}
    )
    @ApiResponse(responseCode = "200", description = "매매노트를 삭제함.")
    @DeleteMapping
    public void deleteNoteByNoteId(@RequestBody NotesDTO notesDTO){
        // 실제로는 삭제 메카니즘이 아니라 상태를 수정함
        notesService.deleteNoteByNoteId(notesDTO);
    }
}