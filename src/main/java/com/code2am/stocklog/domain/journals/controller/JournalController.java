package com.code2am.stocklog.domain.journals.controller;

import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import com.code2am.stocklog.domain.journals.service.JournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "매매일지 API", description = "매매일지를 관리하는 API")
@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    JournalService journalService;


    /* 매매일지 등록 */
    @Operation(
            summary = "매매일지 등록",
            description = "신규 매매일지를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매일지를 성공적으로 등록함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "newJournal", description = "신규로 등록할 매매일지")
    @PostMapping
    public ResponseEntity<JournalDTO> createJournal(@RequestBody JournalDTO newJournal){

        /*
        * 사용자에게 받는 정보
        * stockCode
        * strategyId
        * List<Trade>
        * */


        System.out.println(newJournal);




        return ResponseEntity.ok(newJournal);
    }

    /* 매매일지 조회 */

    /* 매매일지 수정 */

    /* 매매일지 삭제 */


}
