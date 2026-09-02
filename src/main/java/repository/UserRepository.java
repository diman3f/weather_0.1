package repository;

import entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long,User> {
    Optional<User> findByLoginUser(String login);
}
