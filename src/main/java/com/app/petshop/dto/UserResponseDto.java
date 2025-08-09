package com.app.petshop.dto;

import com.app.petshop.domain.PermissionType;

import java.time.LocalDate;

public record UserResponseDto(
        Long id,
        String name,
        LocalDate birthDate,
        String email
)
{ }
