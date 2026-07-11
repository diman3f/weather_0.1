package dao;

import entity.User;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao implements Dao<Long, User> {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User save(User entity) {
        final Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            session.close();
        } catch (PersistenceException e) {
            transaction.rollback();
        }
        return entity;
    }

    @Override
    public void delete(User entity) {
        final Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
            session.close();
        } catch (PersistenceException e) {
            transaction.rollback();
        }
    }


    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long key) {
        return Optional.empty();
    }
}
