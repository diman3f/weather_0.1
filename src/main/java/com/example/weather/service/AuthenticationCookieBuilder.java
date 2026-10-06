package com.example.weather.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public class AuthenticationCookieBuilder {

    public void buildCookie(HttpServletResponse response, UUID idSession) {
        final Cookie cookie = new Cookie("uuid", String.valueOf(idSession));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60);

    }
    
    
}
