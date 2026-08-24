package repository;

import entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLoginUser(String login);
}
