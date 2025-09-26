package com.app.petshop.dto;

import java.time.LocalDate;

public record UserResponseDto(
        Long id,
        String name,
        String login,
        LocalDate birthDate,
        String email
)
{ }
