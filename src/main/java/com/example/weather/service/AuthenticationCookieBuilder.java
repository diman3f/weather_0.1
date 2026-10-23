package com.example.weather.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public class AuthenticationCookieBuilder {

    public void setCookieResponse(HttpServletResponse response, UUID idSession, String name) {
        final Cookie cookie = new Cookie(name, String.valueOf(idSession));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

    }
    
    
}
