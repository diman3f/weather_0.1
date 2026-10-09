package com.example.weather.service;

public interface PasswordEncoder {
    String handleHashPassword(String password);
    boolean checkPassword(String passwordPlaintText, String passwordHashed);
}
