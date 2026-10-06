package com.example.weather.dto;

import lombok.Getter;

@Getter
public class UserRegistrationRequest {
    private String name;
    private String password;

    public UserRegistrationRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
