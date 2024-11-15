package com.cawe.horaperfeita.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String [] ENDPOINTS_UNAUTHENTICATED = {
            "/auth/**"
    };

    public static final String [] ENDPOINTS_AUTHENTICATED = {
            "/"
    };
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                        authorize ->
                                authorize
                                        .requestMatchers(ENDPOINTS_UNAUTHENTICATED).permitAll()
                                        .requestMatchers(ENDPOINTS_AUTHENTICATED).authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
