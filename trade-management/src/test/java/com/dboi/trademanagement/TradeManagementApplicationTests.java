package com.dboi.trademanagement;

import com.dboi.trademanagement.model.BadRequestException;
import com.dboi.trademanagement.model.Trade;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TradeManagementApplicationTests {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @SneakyThrows
    @Test
    public void addValidTrade() {
        Trade trade = buildTrade("T1", 2, "CP-1", "B1",
                "17/04/2021", "17/04/2022");
        Trade tradeResponse = restTemplate.postForObject("/trades", trade, Trade.class);
        assertNotNull(tradeResponse);
    }

    @SneakyThrows
    @Test
    public void addTradeWithLowerVersion() {
        Trade trade1 = buildTrade("T1", 2, "CP-1", "B1",
                "17/04/2021", "17/04/2022");
        restTemplate.postForObject("/trades", trade1, Trade.class);

        Trade trade2 = buildTrade("T1", 1, "CP-1", "B1",
                "17/04/2021", "17/04/2022");

        thrown.expect(BadRequestException.class);
        restTemplate.postForObject("/trades", trade2, Trade.class);
    }

    @SneakyThrows
    @Test
    public void addTradeWithInvalidMaturityDate() {
        Trade trade = buildTrade("T1", 2, "CP-1", "B1",
                "17/04/2021", "16/04/2021");

        thrown.expect(BadRequestException.class);
        restTemplate.postForObject("/trades", trade, Trade.class);
    }

    private Trade buildTrade(String tradeId, Integer version, String counterPartyId, String bookId,
                             String createdDate, String maturityDate) throws ParseException {
        return Trade.builder()
                .tradeId(tradeId)
                .version(version)
                .counterPartyId(counterPartyId)
                .bookId(bookId)
                .createdDate(simpleDateFormat.parse(createdDate))
                .maturityDate(simpleDateFormat.parse(maturityDate))
                .build();
    }

}
