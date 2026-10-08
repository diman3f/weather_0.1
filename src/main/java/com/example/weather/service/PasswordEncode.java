package com.example.weather.service;

public interface PasswordEncode {
    String handleHashPassword(String password);
    boolean checkPassword(String passwordPlaintText, String passwordHashed);
}
