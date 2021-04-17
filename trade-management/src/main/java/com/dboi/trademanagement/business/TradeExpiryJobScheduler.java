package com.dboi.trademanagement.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TradeExpiryJobScheduler {

    private final ScheduledExecutorService scheduledExecutorService;
    private final TradeExpiryJob tradeExpiryJob;

    @Autowired
    public TradeExpiryJobScheduler(final TradeExpiryJob tradeExpiryJob) {
        this.tradeExpiryJob = tradeExpiryJob;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @PostConstruct
    public void scheduleJob() {
        scheduledExecutorService.schedule(tradeExpiryJob, 1, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void cleanup() {
        scheduledExecutorService.shutdown();
    }

}
