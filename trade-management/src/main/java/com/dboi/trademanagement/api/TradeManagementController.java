package com.dboi.trademanagement.api;

import com.dboi.trademanagement.business.TradeManagementService;
import com.dboi.trademanagement.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeManagementController {

    private final TradeManagementService tradeManagementService;

    @Autowired
    public TradeManagementController(final TradeManagementService tradeManagementService) {
        this.tradeManagementService = tradeManagementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Trade addTrade(@Valid @RequestBody final Trade trade) {
        return tradeManagementService.addTrade(trade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trade> getAllTrades() {
        return tradeManagementService.getAllTrades();
    }
}
