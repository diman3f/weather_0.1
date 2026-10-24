package com.example.weather.repository;

import com.example.weather.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final static String FIND_BY_LOGIN = "FROM User WHERE login = :login";
    private final static String FIND_BY_ID = "FROM User WHERE id = :id";


    public JpaUserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public void update(User entity) {

    }

    public Optional<User> findByLoginUser(String login) {
        final TypedQuery<User> query = entityManager.createQuery(FIND_BY_LOGIN, User.class);
        query.setParameter("login", login);
        final List<User> resultList = query.getResultList();

        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.getFirst());
    }

    @Override
    public Optional<User> findByIdUser(String id) {
        final TypedQuery<User> query = entityManager.createQuery(FIND_BY_ID, User.class);
        query.setParameter("id", id);
        final List<User> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.getFirst());

    }
}
