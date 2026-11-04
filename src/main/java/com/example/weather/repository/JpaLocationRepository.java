package com.example.weather.repository;

import com.example.weather.entity.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaLocationRepository implements LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public Location save(Location entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<Location> getAllLocationByUserId(Long userId){
        final String jpqQuery = String.format("SELECT e FROM Location e WHERE e.userId = %s", userId);
        final TypedQuery<Location> query = entityManager.createQuery(jpqQuery, Location.class);
        return query.getResultList();
    }

    @Override
    public void delete(Location entity) {

    }

    @Override
    public void update(Location entity) {

    }
}
