package com.sprint.project.authorization.dto;

public class LoginResponse {

    private String role;

    public LoginResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}