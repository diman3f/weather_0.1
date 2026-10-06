package com.example.weather.repository;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {

    E save(E entity);
    void delete(E entity);

    void update(E entity);
    Optional<E> findById (K id);

}
