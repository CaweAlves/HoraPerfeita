package com.cawe.horaperfeita.application.dtos.user;

import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(@NotNull(message = "name needs to be provided") String name,
                              @NotNull(message = "login needs to be provided") String username,
                              @NotNull(message = "email needs to be provided") String email,
                              @NotNull(message = "password needs to be provided") String password) {
}
