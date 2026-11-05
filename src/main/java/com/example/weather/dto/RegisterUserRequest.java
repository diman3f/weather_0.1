package com.example.weather.dto;

public record RegisterUserRequest(String username, String password, String repeat_password) {
}
