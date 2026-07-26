package service;

import dto.AuthUserDto;
import dto.UserReadDto;
import entity.User;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.Repository;

@Service
@Transactional
public class AuthUserService {

    private final Repository<Long, User> repository;
    private final Flyway flyway;

    public AuthUserService(Flyway flyway, Repository<Long, User> repository) {
        this.repository = repository;
        this.flyway = flyway;
    }

    public AuthUserDto auth(UserReadDto user) {

        User userAuth = new User(user.getName(), user.getPassword());

        repository.save(userAuth);
        return new AuthUserDto(userAuth.getId(), userAuth.getLogin(), "OK");

    }
}

