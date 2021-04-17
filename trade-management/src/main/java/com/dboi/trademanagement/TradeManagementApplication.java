package com.dboi.trademanagement;

import com.dboi.trademanagement.model.Trade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class TradeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeManagementApplication.class, args);
    }

    @Bean("tradeStore")
    public Map<String, Trade> tradeStore() {
        return new ConcurrentHashMap<>();
    }

}
