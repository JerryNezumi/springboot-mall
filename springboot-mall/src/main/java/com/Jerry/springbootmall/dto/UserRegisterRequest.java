package com.Jerry.springbootmall.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String username) {
        this.email = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}