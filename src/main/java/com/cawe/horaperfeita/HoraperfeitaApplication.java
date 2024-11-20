package com.cawe.horaperfeita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cawe.horaperfeita.domain.repositories")
public class HoraperfeitaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoraperfeitaApplication.class, args);
    }

}
