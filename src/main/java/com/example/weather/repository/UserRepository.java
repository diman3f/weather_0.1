package com.example.weather.repository;

import com.example.weather.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long,User> {
    Optional<User> findByLoginUser(String login);
    Optional<User> findByIdUser(String id);
}
