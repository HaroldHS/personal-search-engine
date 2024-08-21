package com.example.personal_search_engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
            auth -> auth.requestMatchers("/api/v1/").permitAll()
                        .requestMatchers("/api/v1/search").permitAll()
        ).csrf(csrf -> csrf.disable())
        .httpBasic(basic -> basic.disable())
        .build();
    }
}
