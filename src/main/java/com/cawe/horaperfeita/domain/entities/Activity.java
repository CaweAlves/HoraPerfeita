package com.cawe.horaperfeita.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "minimum_temperature")
    private BigDecimal minimumTemperature;

    @Column(name = "maximum_temperature")
    private BigDecimal maximumTemperature;
}
