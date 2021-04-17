package com.dboi.trademanagement.business;

import com.dboi.trademanagement.dao.TradeRepository;
import com.dboi.trademanagement.model.BadRequestException;
import com.dboi.trademanagement.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeManagementServiceImpl implements TradeManagementService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeManagementServiceImpl(final TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade addTrade(final Trade trade) {
        validateTradeMaturityDate(trade);
        validateTradeVersion(trade);
        trade.setExpired(false);
        return tradeRepository.addTrade(trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepository.getAllTradeIds().stream().map(tradeRepository::getTrade).collect(Collectors.toList());
    }

    private void validateTradeMaturityDate(final Trade trade) {
        if (trade.getMaturityDate().before(new Date())) {
            throw new BadRequestException("Maturity date for trade cannot be older than today");
        }
    }

    private void validateTradeVersion(final Trade trade) {
        Trade oldTrade = tradeRepository.getTrade(trade.getTradeId());
        if (oldTrade != null && (oldTrade.getVersion() > trade.getVersion())) {
            throw new BadRequestException("Cannot add trade with lower version");
        }
    }

}
