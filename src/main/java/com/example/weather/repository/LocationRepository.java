package com.example.weather.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
@PersistenceContext
public class LocationRepository {
    private EntityManager entityManager;

}
