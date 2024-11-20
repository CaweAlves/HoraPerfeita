package com.cawe.horaperfeita.application.controllers;

import com.cawe.horaperfeita.application.dtos.user.LoginUserDTO;
import com.cawe.horaperfeita.application.dtos.user.RegisterUserDTO;
import com.cawe.horaperfeita.application.dtos.user.ResponseUserTokenDTO;
import com.cawe.horaperfeita.domain.entities.User;
import com.cawe.horaperfeita.domain.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseUserTokenDTO> login(@Valid @RequestBody LoginUserDTO request) throws ResponseStatusException {
        try {
            return ResponseEntity.ok(this.userService.login(request));
        } catch (ResponseStatusException exception) {
            return new ResponseEntity(exception.getMessage(), exception.getStatusCode());
        }

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserDTO request) throws ResponseStatusException {
        try {
            User user = this.userService.create(request);
            return new ResponseEntity(user, HttpStatus.CREATED);
        } catch (ResponseStatusException exception) {
            return new ResponseEntity(exception.getMessage(), exception.getStatusCode());
        }

    }
}