package com.innowisegroup.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {

    List<T> saveAll(Collection<T> entities);
    T save(T entity);
    void delete(T entity);

    Optional<T> findById(I id);
    List<T> findAll();
    List<T> findAll(Specification<T> specification);
}
