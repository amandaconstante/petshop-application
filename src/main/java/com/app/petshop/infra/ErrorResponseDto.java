package com.app.petshop.infra;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(String message, HttpStatus status) {
}
