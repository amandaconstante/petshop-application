package com.app.petshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar teste com POST
                .headers(headers -> headers
                        .defaultsDisabled() // Desabilita todos os headers padrão
                        .frameOptions(frame -> frame.sameOrigin()) // Libera iframes do mesmo domínio// 👈 Permite iframes do mesmo domínio
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera o console H2
                        .anyRequest().permitAll() // Libera acesso a todos os endpoints
                );

        return http.build();
    }
}