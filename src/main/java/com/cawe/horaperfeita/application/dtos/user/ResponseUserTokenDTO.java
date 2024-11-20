package com.cawe.horaperfeita.application.dtos.user;

import jakarta.validation.constraints.NotNull;

public record ResponseUserTokenDTO(@NotNull(message = "required an username") String username,
                                   @NotNull(message = "required an auth token") String token) {
}
