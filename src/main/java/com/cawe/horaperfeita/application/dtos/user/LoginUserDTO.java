package com.cawe.horaperfeita.application.dtos.user;

import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(@NotNull(message = "login needs to be provided") String username,
                           @NotNull(message = "password needs to be provided") String password) {
}
