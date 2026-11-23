package com.example.weather.repository;

import com.example.weather.entity.Location;
import com.example.weather.exception.DataBaseException;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaLocationRepository implements LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Location save(Location entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<Location> getAllLocationByUserId(Long userId) {
        //todo вынести в константу запрос
        final String jpqQuery = String.format("SELECT e FROM Location e WHERE e.userId = %s", userId);
        final TypedQuery<Location> query = entityManager.createQuery(jpqQuery, Location.class);
        return query.getResultList();
    }

    @Override
    public Optional<Location> deleteLocation(Long idLocation, Long userId) {
        try {
            final Optional<Location> location = findById(idLocation, userId);
            if (location.isPresent()) {
                entityManager.remove(location.get());
                return location;
            }
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (PersistenceException e) {
            throw new DataBaseException("Database is not available", e);
        }
        return Optional.empty();
    }

    //todo обернуть в optional
    @Override
    public Optional<Location> findById(Long id, Long userId) {
        final Location singleResult = entityManager.createQuery("SELECT e FROM Location e where e.id = :id and e.userId = :userId", Location.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
        return Optional.of(singleResult);
    }


    @Override
    public void delete(Location entity) {

    }

    @Override
    public void update(Location entity) {

    }
}
