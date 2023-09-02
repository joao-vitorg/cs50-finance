package com.example.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@Transactional
class StockHistoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void findStockHistoryByStockId() throws Exception {
        mockMvc.perform(get("/api/v1/stock/1/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].time").isString())
                .andExpect(jsonPath("$[0].open").isNumber())
                .andExpect(jsonPath("$[0].high").isNumber())
                .andExpect(jsonPath("$[0].close").isNumber())
                .andExpect(jsonPath("$[0].low").isNumber());
    }
}
