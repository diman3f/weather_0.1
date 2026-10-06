package com.example.weather.repository;

import com.example.weather.entity.Session;
import com.example.weather.exception.SessionExpiredException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@PersistenceContext
public class SessionRepository implements SessionUpdateRepository {
    private final EntityManager entityManager;
    private final String getSessionById = "FROM UserSession WHERE id= :id";
    private final String updateExpiresAt = "UPDATE UserSession SET expiresAt = :expiresAt WHERE id = :id";
    private final Integer EXPIRES_AT_ZERO = 0;

    public SessionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Session save(Session session) {
        entityManager.persist(session);
        return session;
    }

    @Override
    public void delete(Session entity) {

    }

    @Override
    public void update(Session entity) {

    }

    @Override
    public void invalidate(Session session) {
        final Query query = entityManager.createQuery(updateExpiresAt);
        final LocalDateTime now = LocalDateTime.of(1900,1,1,0,0);
        query.setParameter("expiresAt", now);
        query.setParameter("id", session.getId());
        query.executeUpdate();

        System.out.println("Сессия аннулирована");
    }

    public Session updateExpiresAt(Session session, Integer minutes) {
        final Query query = entityManager.createQuery(updateExpiresAt);
        final LocalDateTime prolongateExpiresAt = session.getExpiresAt().plusMinutes(minutes);
        query.setParameter("id", session.getId());
        query.setParameter("expiresAt", prolongateExpiresAt);
        query.executeUpdate();
        final Optional<Session> byId = findById(session.getId());
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new SessionExpiredException("AuthUser updateExpiresAt сессия отсутствует");
    }

    @Override
    public Optional<Session> findById(UUID id) {
        final TypedQuery<Session> query = entityManager.createQuery(getSessionById, Session.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
    }
}
