package com.cawe.horaperfeita.infrastructure.config;

import com.cawe.horaperfeita.infrastructure.external.client.openMeteor.OpenMeteor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenMeteor openMeteor(RestTemplate restTemplate) {
        return new OpenMeteor(restTemplate);
    }

}