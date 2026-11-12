package com.example.weather.repository;

import com.example.weather.entity.Location;

import java.util.List;

public interface LocationRepository extends Repository<Long, Location> {
    List<Location> getAllLocationByUserId(Long userId);
}
