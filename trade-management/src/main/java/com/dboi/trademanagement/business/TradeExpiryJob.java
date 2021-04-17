package com.dboi.trademanagement.business;

import com.dboi.trademanagement.dao.TradeRepository;
import com.dboi.trademanagement.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class TradeExpiryJob implements Runnable {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeExpiryJob(final TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void run() {
        Set<String> tradeIds = tradeRepository.getAllTradeIds();
        Date today = new Date();
        tradeIds.forEach(tradeId -> {
            Trade trade = tradeRepository.getTrade(tradeId);
            if (trade.getMaturityDate().before(today)) {
                trade.setExpired(true);
            }
            tradeRepository.addTrade(trade);
        });
    }
}
