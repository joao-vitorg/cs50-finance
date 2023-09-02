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
class StockControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllStocks() throws Exception {
        mockMvc.perform(get("/api/v1/stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(8));
    }

    @Test
    void findStockById() throws Exception {
        mockMvc.perform(get("/api/v1/stock/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.symbol").value("AAPL"));
    }

    @Test
    void findStockByIdInvalid() throws Exception {
        mockMvc.perform(get("/api/v1/stock/900"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertStock() throws Exception {
        String body = "{\"symbol\": \"TEST\", \"name\": \"Test stock\", \"price\": 99.99}";

        mockMvc.perform(post("/api/v1/stock")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.symbol").value("TEST"));
    }

    @Test()
    void insertStockDuplicated() throws Exception {
        String body = "{\"symbol\": \"AAPL\", \"name\": \"Test stock\", \"price\": 99.99}";

        mockMvc.perform(post("/api/v1/stock")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Entry already exists."));
    }

    @Test()
    void insertStockValidationError() throws Exception {
        String body = "{\"symbol\": \"Test stock\", \"name\": \"Test stock\"}";

        mockMvc.perform(post("/api/v1/stock")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("{symbol=must match \"^[A-Z0-9]+$\", price=must not be null}"));
    }
}
