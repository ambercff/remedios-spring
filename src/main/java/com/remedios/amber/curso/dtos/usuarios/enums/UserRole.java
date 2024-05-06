package com.remedios.amber.curso.dtos.usuarios.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.apache.catalina.User;

@Getter
public enum UserRole {
    ROLE_ADMIN("admin"),
    ROLE_MANAGER("manager"),
    ROLE_USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()){
            if (userRole.name().equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Role inválida!");
    }
}
