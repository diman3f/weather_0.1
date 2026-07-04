package service;

import dto.AuthUserDto;
import dto.UserReadDto;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final SessionFactory sessionFactory;


    public AuthUserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AuthUserDto auth(UserReadDto user) {
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

