package com.example.weather.repository;

import com.example.weather.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends Repository<Long, Location> {
    List<Location> getAllLocationByUserId(Long userId);

    Optional<Location> deleteLocation(Long idLocation, Long userId);

    Optional<Location> findById(Long id, Long userId);
}
