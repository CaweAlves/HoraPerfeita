package com.cawe.horaperfeita.application.dtos.activity;

import java.math.BigDecimal;
import java.util.Date;

public record ActivityResponseDTO(
        String name,
        Date date,
        String hour,
        BigDecimal minimum_temperature,
        BigDecimal maximum_temperature
) {
}
