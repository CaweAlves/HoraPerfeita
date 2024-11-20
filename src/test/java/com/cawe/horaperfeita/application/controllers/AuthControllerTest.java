package com.cawe.horaperfeita.application.controllers;

import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should be able to register a new user in the application")
    public void shouldBeAbleToRegisterANewUserInTheApplication() throws Exception {
        RegisterUserDTO request = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

        String url = "/auth/register";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(request.name()))
                .andExpect(jsonPath("$.username")
                        .value(request.username()))
                .andExpect(jsonPath("$.email")
                        .value(request.email()));
    }

    @Test
    @DisplayName("should make sure to inform the user of an error when the user already exists")
    public void shouldMakeSureToInformeTheUserOfAnErrorWhenTheUserAlreadyExists() throws Exception {
        RegisterUserDTO request = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

        String url = "/auth/register";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(request.name()))
                .andExpect(jsonPath("$.username")
                        .value(request.username()))
                .andExpect(jsonPath("$.email")
                        .value(request.email()));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$")
                        .value("409 CONFLICT \"User already exists\""));
    }
}