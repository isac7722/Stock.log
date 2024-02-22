package com.code2am.stocklog.domain.journals.service;

import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import com.code2am.stocklog.domain.journals.model.dto.TradeDTO;
import com.code2am.stocklog.domain.journals.model.entitiy.Journal;
import com.code2am.stocklog.domain.journals.model.entitiy.Trade;
import com.code2am.stocklog.domain.journals.repository.JournalsRepository;
import com.code2am.stocklog.domain.journals.repository.TradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalsService {

    @Autowired
    JournalsRepository journalsRepository;

    @Autowired
    TradesRepository tradesRepository;

    // 새 매매일지를 등록하는 매소드
    public void createJournal(JournalDTO journalDTO) {

        Journal savedJournal = journalsRepository.save(convertToJournal(journalDTO));

        List<Trade> result = tradesRepository.saveAll(createTrades(savedJournal));



    }


    public List<Trade> createTrades(Journal journal){

        List<Trade> trades = journal.getTrades();

        for (Trade trade : trades) {
            trade.setJournalId(journal.getJournalId());
        }

        return trades;

    }

    public Journal convertToJournal(JournalDTO journalDTO){
        Journal journal = new Journal();

        journal.setTrades(convertToTradeList(journalDTO.getTrades()));
        journal.setJournalId(journalDTO.getJournalId());
        journal.setStockCode(journalDTO.getStockCode());
        journal.setLastTradedDate(journalDTO.getLastTradedDate());
        journal.setBuyPrice(journalDTO.getBuyPrice());
        journal.setBuyQty(journalDTO.getBuyQty());
        journal.setSellPrice(journalDTO.getSellPrice());
        journal.setSellQty(journalDTO.getSellQty());
        journal.setProfit(journalDTO.getProfit());
        journal.setStatus(journalDTO.getStatus());
        journal.setUserId(journalDTO.getUserId());
        journal.setStrategyId(journalDTO.getStrategyId());


        return journal;

    }

    public static List<Trade> convertToTradeList(List<TradeDTO> tradeDTOList) {
        return tradeDTOList.stream()
                .map(JournalsService::convertToTrade)
                .collect(Collectors.toList());
    }

    public static Trade convertToTrade(TradeDTO tradeDTO) {

        Trade trade = new Trade();

        trade.setTradeId(tradeDTO.getTradeId());
        trade.setStatus(tradeDTO.getStatus());
        trade.setTradeDate(tradeDTO.getTradeDate());
        trade.setPrice(tradeDTO.getPrice());
        trade.setQuantity(tradeDTO.getQuantity());
        trade.setActivateStatus(tradeDTO.getActivateStatus());
        return trade;
    }


}


