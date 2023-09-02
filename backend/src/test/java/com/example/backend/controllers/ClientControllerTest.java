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
class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllClients() throws Exception {
        mockMvc.perform(get("/api/v1/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    void findClientById() throws Exception {
        mockMvc.perform(get("/api/v1/client/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("joao"))
                .andExpect(jsonPath("$.balance").value(1000));
    }

    @Test
    void findClientByIdInvalid() throws Exception {
        mockMvc.perform(get("/api/v1/client/900"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertClient() throws Exception {
        String body = "{\"username\": \"test\", \"password\": \"test123\"}";

        mockMvc.perform(post("/api/v1/client")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.balance").value(10000));
    }

    @Test()
    void insertClientDuplicated() throws Exception {
        String body = "{\"username\": \"vitor\", \"password\": \"test123\"}";

        mockMvc.perform(post("/api/v1/client")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Entry already exists."));
    }

    @Test()
    void insertClientValidationError() throws Exception {
        String body = "{\"username\": \"João\"}";

        mockMvc.perform(post("/api/v1/client")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("{password=must not be blank, username=must match \"^[a-zA-Z0-9_-]+$\"}"));
    }
}
