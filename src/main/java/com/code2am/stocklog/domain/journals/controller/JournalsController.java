package com.code2am.stocklog.domain.journals.controller;

import com.code2am.stocklog.domain.auth.common.util.SecurityUtil;
import com.code2am.stocklog.domain.auth.jwt.util.TokenUtils;
import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import com.code2am.stocklog.domain.journals.model.dto.TradeDTO;
import com.code2am.stocklog.domain.journals.model.entitiy.Journal;
import com.code2am.stocklog.domain.journals.service.JournalsService;
import com.code2am.stocklog.domain.users.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;


@Tag(name = "매매일지 API", description = "매매일지를 관리하는 API")
@RestController
@RequestMapping("/journals")
public class JournalsController {

    @Autowired
    JournalsService journalsService;

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UsersRepository usersRepository;


    /* 매매일지 등록 */
    @Operation(
            summary = "매매일지 등록",
            description = "신규 매매일지를 등록합니다.",
            tags = {"JournalsController","post","Journals"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매일지를 성공적으로 등록함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "createJournal", description = "신규로 등록할 매매일지")
    @PostMapping
    public ResponseEntity<JournalDTO> createJournal(@RequestBody JournalDTO journalDTO){

        // 매매일지의 정보를 업데이트
        JournalDTO newJournal = journalInfoUpdate(journalDTO);

        // 새 매매일지를 등록
        journalsService.createJournal(newJournal);

        return ResponseEntity.ok(newJournal);
    }

    /* 매매일지 조회 */
    @Operation(
            summary = "매매일지 조회",
            description = "사용자의 매매일지를 조회합니다",
            tags = {"JournalsController","get","Journals"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매일지를 성공적으로 조회함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 조회되지 않음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "searchJournal", description = "조회할 매매일지")
    @GetMapping("/search")
    public ResponseEntity <List<JournalDTO>> readJournalsByUserId(HttpServletRequest request) {

        String email = tokenUtils.getEmailFromJwtToken(request);
        System.out.println("사용자의 이메일: "+email);

        // 임시방편 코드
        Integer userId = usersRepository.findByEmail(email).get().getUserId();


        List<JournalDTO> journals = journalsService.readJournalsByUserId(userId);

        System.out.println(journals);
        return ResponseEntity.ok(journals);
    }






    /* 매매일지 수정 */
    @Operation(
            summary = "매매일지 변경",
            description = "사용자의 매매일지를 변경합니다.",
            tags = {"JournalsController","update","Journals"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매일지를 성공적으로 변경함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 변경되지 않음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "update", description = "변경할 매매일지")
    @PostMapping("/update")
    public ResponseEntity <String> updateJournal(@RequestBody JournalDTO journalDTO) {


        JournalDTO updateJournal = journalInfoUpdate(journalDTO);
        // 매매일지 변경
        journalsService.updateJournal(updateJournal);

        return ResponseEntity.ok("변경성공");
    }




    /* 매매일지 삭제 */
    @Operation(
            summary = "매매일지 삭제",
            description = "사용자의 매매일지를 삭제합니다",
            tags = {"JournalsController","delete","Journals"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매매일지를 성공적으로 삭제함."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 조회되지 않음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "deleteJournal", description = "삭제할 매매일지")
    @DeleteMapping("/delete")
    public ResponseEntity <String> deleteJournal(@RequestBody JournalDTO journal) {

        JournalDTO deleteJournal = journalInfoUpdate(journal);
        // 실제로는 삭제 메카니즘이 아니라 상태를 수정함
        String message = journalsService.deleteJournalByJournalId(deleteJournal);

        return ResponseEntity.ok(message);
    }










    // 입력받은 값을 통해 journalDTO의 정보를 업데이트 하는 메소드
    public JournalDTO journalInfoUpdate(JournalDTO journalDTO){
        /* 유저 아이디를 입력 */
        journalDTO.setUserId(securityUtil.getUserId());

        /* 최종 거래일 입력 */
        journalDTO.setLastTradedDate(getLastTradedDate(journalDTO));

        /* 평균 매수가 입력 */
        journalDTO.setBuyPrice(getAverageBuyPrice(journalDTO));

        /* 매수 총량 입력 */
        journalDTO.setBuyQty(getTotalBuyQuantity(journalDTO));

        /* 매도 평균가 입력*/
        journalDTO.setSellPrice(getAverageSellPrice(journalDTO));

        /* 매도 총량 입력 */
        journalDTO.setSellQty(getTotalSellQuantity(journalDTO));

        /* 수익률 입력 */
        journalDTO.setProfit(getProfit(journalDTO));

        return journalDTO;
    }




    /* 최종 거래일을 구하는 메소드*/
    public LocalDateTime getLastTradedDate(JournalDTO journal){

        LocalDateTime lastTradedDate = journal.getTrades().stream()
                .map(TradeDTO::getTradeDate) // TradeDTO에서 tradeDate만 추출
                .max(LocalDateTime::compareTo) // 최대값 찾기
                .orElse(null); // 만약 trades가 비어있으면 null 반환

        return lastTradedDate;
    }

    /* 매수 평균가를 구하는 매소드 */
    public Integer getAverageBuyPrice(JournalDTO journal){

        OptionalDouble averageBuyPrice = journal.getTrades().stream()
                .filter(trade -> "buy".equals(trade.getStatus())) // trades의 중에 status가 buy인 친구들만 고른다
                .mapToDouble(TradeDTO::getPrice)
                .average(); // 평균 계산

        // buyPrice 값이 있는 경우 Integer 반환
        if (averageBuyPrice.isPresent()) {

            double doubleResult = averageBuyPrice.getAsDouble();
            // 데이터 타입이 달라서 형변환
            Integer integerResult = (int) doubleResult;

            return integerResult;
        } else {
            // buyPrice 값이 없는 경우 null 반환
           return null;
        }
    }

    /* 매수 총량을 구하는 매소드 */
    public Integer getTotalBuyQuantity(JournalDTO journal){

        double totalBuyQty = journal.getTrades().stream()
                .filter(trade -> "buy".equals(trade.getStatus()))
                .mapToDouble(TradeDTO::getQuantity)
                .sum();

        Integer result = (int)totalBuyQty;

        return result;
    }

    /* 매도 평균가를 구하는 매소드 */
    public Integer getAverageSellPrice(JournalDTO journal){

        OptionalDouble averageSellPrice = journal.getTrades().stream()
                .filter(trade -> "sell".equals(trade.getStatus())) // trades의 중에 status가 sell인 친구들만 고른다
                .mapToDouble(TradeDTO::getPrice)
                .average();

        // sellPrice 값이 있는 경우 Integer 반환
        if (averageSellPrice.isPresent()) {

            double doubleResult = averageSellPrice.getAsDouble();
            // 데이터 타입이 달라서 형변환
            Integer integerResult = (int) doubleResult;

            return integerResult;
        } else {
            // sellPrice 값이 없는 경우 null 반환
            return null;
        }
    }


    /* 매도 총량을 구하는 매소드 */
    public Integer getTotalSellQuantity(JournalDTO journal){

        double totalBuyQty = journal.getTrades().stream()
                .filter(trade -> "sell".equals(trade.getStatus()))
                .mapToDouble(TradeDTO::getQuantity)
                .sum();

        Integer result = (int)totalBuyQty;

        return result;
    }

    /* 수익률을 구하는 메소드 */
    public Integer getProfit(JournalDTO journal){

        if ((getAverageBuyPrice(journal)) > 0 ){

            // 매수 총액을 구함
            Integer buyBalance = (getAverageBuyPrice(journal)) * (getTotalBuyQuantity(journal));

            // 매도 총액을 구함
            Integer sellBalance = (getAverageSellPrice(journal)) * (getTotalSellQuantity(journal));

            // 수익금을 반환
            return sellBalance - buyBalance;
        }
        else {
            // 매도기록이 없는 경우 null 를 반환
            return null;
        }
    }


}




