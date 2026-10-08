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
    private final String hql = "FROM User WHERE login = :login";


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

    @Override
    public Optional<User> findById(Long key) {

        return Optional.empty();
    }

    public Optional<User> findByLoginUser(String login) {
        final TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("login", login);
        final List<User> resultList = query.getResultList();

        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.getFirst());


    }
}
