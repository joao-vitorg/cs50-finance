package com.example.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@Transactional
class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllTransactions() throws Exception {
        mockMvc.perform(get("/api/v1/transaction"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(3));
    }

    @Test
    void findTransactionByClientId() throws Exception {
        mockMvc.perform(get("/api/v1/transaction/client/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    void insertTransactionBuy() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 1, \"shares\": 2}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.stock.id").value(1))
                .andExpect(jsonPath("$.shares").value(2));
    }

    @Test
    void insertTransactionBuyInsufficientFunds() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 1, \"shares\": 900}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Insufficient funds."));
    }

    @Test
    void insertTransactionSell() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 1, \"shares\": -2}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.stock.id").value(1))
                .andExpect(jsonPath("$.shares").value(-2));
    }

    @Test
    void insertTransactionSellWithoutStocks() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 1, \"shares\": -9}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Cliente don't have Stock."));
    }

    @Test
    void insertTransactionZeroShares() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 1, \"shares\": 0}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Shares cannot be 0."));
    }

    @Test
    void insertTransactionValidationError() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": -1}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("{shares=must not be null, stockId=must be greater than or equal to 1}"));
    }

    @Test
    void insertTransactionInvalidStock() throws Exception {
        String body = "{\"clientId\": 1, \"stockId\": 90, \"shares\": 1}";

        mockMvc.perform(post("/api/v1/transaction")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Unable to find com.example.backend.models.Stock with id 90"));
    }
}
