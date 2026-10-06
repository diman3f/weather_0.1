package com.example.weather.service;

import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.dto.RegisterUserResponse;

public interface RegistrationService {
    RegisterUserResponse register(RegisterUserRequest dto);
}
