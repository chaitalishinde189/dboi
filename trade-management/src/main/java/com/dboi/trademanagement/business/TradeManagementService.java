package com.dboi.trademanagement.business;

import com.dboi.trademanagement.model.Trade;

import java.util.List;

public interface TradeManagementService {

    Trade addTrade(final Trade trade);

    List<Trade> getAllTrades();
}
