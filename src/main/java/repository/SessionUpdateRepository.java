package repository;

import entity.Session;

import java.io.Serializable;
import java.util.UUID;

public interface SessionUpdateRepository extends Repository<UUID, Session> {
     Session updateExpiresAt(Session session, Integer minutes);
     void invalidate(Session session);
}