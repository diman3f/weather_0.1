package dao;

public interface Dao<E extends > {

    E save(E entity);
    E update(K key);
    E findById(K key);

}
