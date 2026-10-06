package com.example.weather.exception;

import lombok.Getter;

@Getter
public class AuthenticationCredentialsNotFoundException extends RuntimeException {
    private String userName;
    public AuthenticationCredentialsNotFoundException(String message, String userName) {
        super(message);
        this.userName = userName;
    }

}
