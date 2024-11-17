package com.cawe.horaperfeita.application.controllers;

import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.cawe.horaperfeita.domain.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("should be able to register a new user in the application")
    public void shouldBeAbleToRegisterANewUserInTheApplication() throws JsonProcessingException {
        User userForValidation = new User("Cawe", "cawe.alves", "contato@cawe.dev", "password");
        RegisterUserDTO request = new RegisterUserDTO("Cawe", "cawe.alves", "contato@cawe.dev", "password");

        String url = "/auth/register";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<RegisterUserDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        String responseBody = response.getBody();
        assertNotNull(responseBody);
        assertTrue(responseBody.startsWith("{") && responseBody.endsWith("}"), "Response body is not JSON");

        User createdUser = new ObjectMapper().readValue(response.getBody(), User.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userForValidation.getName(), createdUser.getName());
        assertEquals(userForValidation.getUsername(), createdUser.getUsername());
        assertEquals(userForValidation.getEmail(), createdUser.getEmail());
    }

}