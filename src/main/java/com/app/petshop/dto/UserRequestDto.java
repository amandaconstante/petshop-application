package com.app.petshop.dto;

import com.app.petshop.domain.UserRole;

import java.time.LocalDate;

public record UserRequestDto(
        String name,
        String login,
        LocalDate birthDate,
        String email,
        String password,
        UserRole permission
)
{ }
