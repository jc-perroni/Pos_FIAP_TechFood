package com.posfiap.techfood.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> findById(Long id);
    List<T> findAll(int size, int offset);
    Integer update(T object, long id);
    Integer save(T object);
    Integer delete(long id);

}
