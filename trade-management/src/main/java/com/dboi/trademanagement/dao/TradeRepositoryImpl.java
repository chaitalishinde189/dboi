package com.dboi.trademanagement.dao;

import com.dboi.trademanagement.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private final Map<String, Trade> tradeStore;

    @Autowired
    public TradeRepositoryImpl(@Qualifier("tradeStore") final Map<String, Trade> tradeStore) {
        this.tradeStore = tradeStore;
    }

    @Override
    public Trade addTrade(final Trade trade) {
        tradeStore.put(trade.getTradeId(), trade);
        System.out.println("---" + trade);
        return trade;
    }

    @Override
    public Trade getTrade(final String tradeId) {
        return tradeStore.get(tradeId);
    }

    @Override
    public Set<String> getAllTradeIds() {
        return tradeStore.keySet();
    }

}
