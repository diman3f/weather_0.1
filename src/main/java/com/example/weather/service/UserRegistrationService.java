package com.example.weather.service;

import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.dto.RegisterUserResponse;
import com.example.weather.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.weather.repository.Repository;

@Service
@Transactional
@Slf4j
public class UserRegistrationService implements RegistrationService{
    private final Repository<Long, User> repository;
    private final PasswordEncode passwordGenerator;

    public UserRegistrationService(Repository<Long, User> repository, PasswordEncode passwordGenerator) {
        this.repository = repository;
        this.passwordGenerator = passwordGenerator;
    }

    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        String name = registerUserRequest.username();
        String hash = passwordGenerator.handleHashPassword(registerUserRequest.password());

        User user = new User(name, hash);
        final User save = repository.save(user);
        log.error("Error registration");
        return new RegisterUserResponse(save.getId(), save.getLogin());
    }
}
