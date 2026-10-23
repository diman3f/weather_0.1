package com.example.weather.service;

import com.example.weather.dto.RegisterUserRequest;
import com.example.weather.dto.RegisterUserResponse;
import com.example.weather.entity.Session;
import com.example.weather.entity.User;
import com.example.weather.exception.DataBaseException;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.mapping.Constraint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.weather.repository.Repository;

@Service
@Transactional
@Slf4j
public class UserRegistrationService implements RegistrationService {
    private final Repository<Long, User> repository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(Repository<Long, User> repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        String name = registerUserRequest.username();
        String hash = passwordEncoder.handleHashPassword(registerUserRequest.password());

        User user = new User(name, hash);
        try {
            final User userEntity = repository.save(user);
            return new RegisterUserResponse(userEntity.getId(), userEntity.getLogin());
        } catch (PersistenceException e) {
            if (e instanceof ConstraintViolationException) {
                log.error("Failed to save user due to constraint violation", e);
                throw new DataBaseException(
                        String.format("Failed to save user %s due to constraint violation", user.getLogin()) , e);
            }
            throw new DataBaseException("Failed to save user", e);
        }
    }
}
