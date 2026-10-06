package com.example.weather.service;


import com.example.weather.dto.AuthUserResponse;
import com.example.weather.entity.Session;
import com.example.weather.entity.User;
import com.example.weather.exception.AuthenticationCredentialsNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.weather.repository.JpaUserRepository;
import com.example.weather.repository.Repository;
import com.example.weather.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class AuthUserService {

    private final Repository<Long, User> repository;
    private final UserRepository userRepository;
    private final SessionService sessionService;


    public AuthUserService(JpaUserRepository repository, SessionService sessionService) {
        this.repository = repository;
        this.userRepository = repository;
        this.sessionService = sessionService;

    }


    public void invalidateUser(UUID id) {
        sessionService.invalidateSession(id);
    }

    public AuthUserResponse authenticateUser(String login, String password) {
        final Optional<User> entity = userRepository.findByLoginUser(login);
        if (entity.isPresent()) {

            final User user = entity.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                final Session session = sessionService.create(user);
            return new AuthUserResponse(user.getId(), user.getLogin(), session.getId());
        } else {
                log.error("Not correct password");
                throw new AuthenticationCredentialsNotFoundException(String.format("User %s do not register", login), login);
            }
        }
            // на сервеной части нужно кидать конктретные исключения на логин или пароль
        throw new AuthenticationCredentialsNotFoundException(String.format("User %s do not register", login), login);
    }


}

