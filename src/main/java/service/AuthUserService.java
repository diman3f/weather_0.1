package service;

import dto.AuthUserDto;
import dto.UserReadDto;
import entity.User;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final SessionFactory sessionFactory;
    private final Flyway flyway;

    public AuthUserService(SessionFactory sessionFactory, Flyway flyway) {
        this.sessionFactory = sessionFactory;
        this.flyway = flyway;

    }

    public AuthUserDto auth(UserReadDto user) {
        final String database = flyway.migrate().schemaName;
        System.out.println(database);
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        User userAuth = new User("123");
        session.persist(userAuth);
        transaction.commit();

        session.close();
        sessionFactory.close();
        return new AuthUserDto(2L, "qqwe", "ok");

    }
}

