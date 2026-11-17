package com.example.weather.repository;

import com.example.weather.entity.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public void deleteLocation(Long idLocation) {
        final Location location = findById(idLocation);
        if(location != null) {
            entityManager.remove(location);
        }
    }

//todo обернуть в optional
    @Override
    public Location findById(Long id) {
        return entityManager.find(Location.class, id);
    }


    public Optional<Location> find(Long id, Long userId) {
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
