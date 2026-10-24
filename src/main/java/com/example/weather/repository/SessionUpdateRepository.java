package com.example.weather.repository;

import com.example.weather.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionUpdateRepository extends Repository<UUID, Session> {
    Session updateExpiresAt(Session session, Integer minutes);

    void invalidate(Session session);

    Optional<Session> findById(UUID id);
}