package dao;

import entity.BaseEntity;

import java.io.Serializable;
import java.util.Optional;

public interface Dao<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);
    void delete(E entity);
    void update(E entity);
    Optional<E> findById (K key);

}
