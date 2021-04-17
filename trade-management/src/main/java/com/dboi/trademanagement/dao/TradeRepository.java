package com.dboi.trademanagement.dao;

import com.dboi.trademanagement.model.Trade;

import java.util.Map;
import java.util.Set;

public interface TradeRepository {

    Trade addTrade(final Trade trade);

    Trade getTrade(final String tradeId);

    Set<String> getAllTradeIds();

}
