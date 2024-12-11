package com.cawe.horaperfeita.application.controllers;

import com.cawe.horaperfeita.application.dtos.user.LoginUserDTO;
import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username")
                        .value(request.username()))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    @DisplayName("should make sure to inform the user of an error when the user already exists")
    public void shouldMakeSureToInformTheUserOfAnErrorWhenTheUserAlreadyExists() throws Exception {
        RegisterUserDTO request = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

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

    @Test
    @DisplayName("should be able to login in the application")
    public void shouldBeAbleToLoginInTheApplication() throws Exception {
        RegisterUserDTO registerRequest = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(registerRequest.name()))
                .andExpect(jsonPath("$.username")
                        .value(registerRequest.username()))
                .andExpect(jsonPath("$.email")
                        .value(registerRequest.email()));

        LoginUserDTO loginRequest = new LoginUserDTO("cawe.alves", "password");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username")
                        .value(loginRequest.username()))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    @DisplayName("should make sure to inform the user of an error when user credentials do not match")
    public void shouldMakeSureToInformTheUserOfAnErrorWhenUserCredentialsDoNotMatch() throws Exception {
        RegisterUserDTO registerRequest = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(registerRequest.name()))
                .andExpect(jsonPath("$.username")
                        .value(registerRequest.username()))
                .andExpect(jsonPath("$.email")
                        .value(registerRequest.email()));

        LoginUserDTO loginRequest = new LoginUserDTO("cawe.alves", "incorrectPassword");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$")
                        .value("401 UNAUTHORIZED \"User credentials do not match\""));
    }
}