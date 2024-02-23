package com.code2am.stocklog.domain.journals.controller;

import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import com.code2am.stocklog.domain.journals.model.dto.TradeDTO;
import com.code2am.stocklog.domain.journals.service.JournalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.OptionalDouble;
import java.util.OptionalInt;


@Tag(name = "매매일지 API", description = "매매일지를 관리하는 API")
@RestController
@RequestMapping("/journals")
public class JournalsController {

    @Autowired
    JournalsService journalsService;


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
    @Parameter(name = "newJournal", description = "신규로 등록할 매매일지")
    @PostMapping
    public ResponseEntity<JournalDTO> createJournal(@RequestBody JournalDTO newJournal){

        /*
        프런트에서 사용자에게 받는 정보
        Integer stockCode
        Integer strategyId
        String status;
        List<TradeDTO> trades
        List<NoteDTO> notes
        */


        /* 자바에서 처리할 데이터*/


        /* newJournal에 userId 입력 */
        // 현재 사용중인 사용자를 지정 (currentUser)
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 되어있는 SecurityContextHolder 안에 있는 AuthDetails 정보를 가져옴
        // AuthDetails 안에 있는 userNo 정보를 가져옴

        /*===============================================*/
        /* 지금 AuthDetails가 아직 작업이 안되어 있는 관계로 작업이 끝나면 추가 예정 */




       /* 최종 거래일 입력 */
        newJournal.setLastTradedDate(getLastTradedDate(newJournal));

        /* 평균 매수가 입력 */
        newJournal.setBuyPrice(getAverageBuyPrice(newJournal));

        /* 매수 총량 입력 */
        newJournal.setBuyQty(getTotalBuyQuantity(newJournal));

        /* 매도 평균가 입력*/
        newJournal.setSellPrice(getAverageSellPrice(newJournal));

        /* 매도 총량 입력 */
        newJournal.setSellQty(getTotalSellQuantity(newJournal));

        /* 수익률 입력 */
        newJournal.setProfit(getProfit(newJournal));


        // 새 매매일지를 등록
        journalsService.createJournal(newJournal);



        return ResponseEntity.ok(newJournal);
    }

    /* 매매일지 조회 */

    /* 매매일지 수정 */

    /* 매매일지 삭제 */
















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




