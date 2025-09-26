package com.app.petshop.domain;

import lombok.Getter;

@Getter
public enum UserRole {
     ADMIN("Administrador do sistema"),
    COMMOM("Usuário comum");

     private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
