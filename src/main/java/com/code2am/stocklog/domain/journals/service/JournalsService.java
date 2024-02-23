package com.code2am.stocklog.domain.journals.service;

import com.code2am.stocklog.domain.journals.dao.JournalsDAO;
import com.code2am.stocklog.domain.journals.model.dto.JournalDTO;
import com.code2am.stocklog.domain.journals.model.dto.TradeDTO;
import com.code2am.stocklog.domain.journals.model.entitiy.Journal;
import com.code2am.stocklog.domain.journals.model.entitiy.Trade;
import com.code2am.stocklog.domain.journals.repository.JournalsRepository;
import com.code2am.stocklog.domain.journals.repository.TradesRepository;
import com.code2am.stocklog.domain.notes.dao.NotesDAO;
import com.code2am.stocklog.domain.journals.dao.TradesDAO;
import com.code2am.stocklog.domain.notes.models.dto.NotesDTO;
import com.code2am.stocklog.domain.notes.models.entity.Notes;
import com.code2am.stocklog.domain.notes.models.vo.NotesVo;
import com.code2am.stocklog.domain.notes.service.NotesService;
import jakarta.transaction.Transactional;
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

    @Autowired
    NotesService notesService;

    @Autowired
    JournalsDAO journalsDAO;

    @Autowired
    TradesDAO tradesDAO;

    @Autowired
    NotesDAO notesDAO;

    // 새 매매일지를 등록하는 매소드
    @Transactional
    public void createJournal(JournalDTO journalDTO) {

        // 먼저 매매일지를 저장하고 저장된 매매일지 객체를 반환받는다
        Journal savedJournal = journalsRepository.save(convertToJournal(journalDTO));

        // 반환 받은 매매일지의 pk를 기준으로 매매기록(들)을 저장한다
        tradesRepository.saveAll(createTrades(savedJournal));

        // 반환 받은 매매일지의 pk를 기준으로 매매노트(들)을 저장한다
        List<NotesDTO> notesDTOS = journalDTO.getNotes();
        for( NotesDTO notesDTO : notesDTOS){
            notesDTO.setJournalId(savedJournal.getJournalId());
            notesService.createNoteByJournalId(notesDTO);
        }

    }


    // 유저의 매매일지를 조회하는 매소드
    @Transactional
    public List<JournalDTO> readJournalsByUserId(Integer userId){

        List<JournalDTO> journals = journalsDAO.readJournalsByUserId(userId);

        System.out.println("journals: "+journals);

        // 매매일지에 관련된 매매기록을 가져온다
        for (JournalDTO journal : journals){
            List<TradeDTO> trades = tradesDAO.readTradesByJournalId(journal.getJournalId());

            journal.setTrades(trades);
        }

        // 매매일지에 관련된 노트를 가져온다
        for (JournalDTO journal : journals){
            List<NotesVo> notes = notesDAO.readNotesByJournalId(journal.getJournalId());

            journal.setNotesVo(notes);
        }

        return journals;
    }


    // 매매일지를 지우는 매소드
    @Transactional
    public String deleteJournalByJournalId(JournalDTO journal) {

        Journal deleteJournal = convertToJournal(journal);

        // 해당 매매일지의 매매기록들의 상태를 변환한다
        List<Trade> deleteTrades = deleteJournal.getTrades();

        for (Trade trade : deleteTrades) {
            trade.setActivateStatus("N");
        }

        tradesRepository.saveAll(deleteTrades);

        // 해당 매매일지의 매매노트들의 상태를 변환한다
        List<NotesDTO> deleteNotes = journal.getNotes();

        for (NotesDTO note : deleteNotes){
            notesService.deleteNoteByNoteId(note);
        }

        // 매매일지의 상태를 변환한다
        deleteJournal.setStatus("N");

        journalsRepository.save(deleteJournal);

        return "삭제 성공";
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
        journal.setStatus("Y");
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
        trade.setActivateStatus("Y");
        return trade;
    }



}


