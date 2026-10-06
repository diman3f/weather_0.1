package com.example.weather.service;

import com.example.weather.entity.Session;
import com.example.weather.entity.User;
import com.example.weather.exception.SessionExpiredException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.weather.repository.SessionRepository;
import com.example.weather.repository.SessionUpdateRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionService.class);
    private final SessionUpdateRepository sessionRepository;
    private static final Integer PROLONGATION_HOURS = 1;
    private static final Integer PROLONGATION_MINUTES = 10;


    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session create(User user) {
        UUID uuid = generate();
        final Session session = new Session(uuid, user, LocalDateTime.now().plusHours(PROLONGATION_HOURS));
        return save(session);
    }

    public void invalidateSession(UUID id) {
        final Session validSession = getValidSession(id);
        sessionRepository.invalidate(validSession);
    }

    public Session getValidSession(UUID id) {
        final Optional<Session> optional = sessionRepository.findById(id);
        if (optional.isPresent()) {
            final Session session = optional.get();
            final LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.isBefore(session.getExpiresAt())) {
                return session;
            }
            throw new SessionExpiredException("Сессия просрочена");
        }
        throw new EntityNotFoundException("Session not found");
    }

    public Session prolongate(Session session) {
        return sessionRepository.updateExpiresAt(session, PROLONGATION_MINUTES);
    }

    private Session save(Session session) {
        sessionRepository.save(session);
        return session;
    }

    private UUID generate() {
        return UUID.randomUUID();
    }
}
