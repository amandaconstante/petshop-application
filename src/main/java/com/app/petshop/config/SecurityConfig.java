package com.app.petshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para facilitar teste com POST
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // configura autenticação stateless
            .headers(headers -> headers
                    .defaultsDisabled() // Desabilita todos os headers padrão
                    .frameOptions(frame -> frame.sameOrigin()) // Libera iframes do mesmo domínio// 👈 Permite iframes do mesmo domínio
            )
            .authorizeHttpRequests(auth -> auth // CONFIGRUAR AQUI autorização para admin/user
                    .requestMatchers("/h2-console/**").permitAll() // Libera o console H2
                    .anyRequest().permitAll() // Libera acesso a todos os endpoints
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}