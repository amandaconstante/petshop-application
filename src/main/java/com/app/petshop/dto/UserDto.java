package com.app.petshop.dto;

import com.app.petshop.domain.PermissionType;

import java.time.LocalDate;

public record UserDto(String name, LocalDate birthDate, String email, String password, PermissionType permission) { }
