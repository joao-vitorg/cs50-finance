package com.example.backend.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseService<T> {
    JpaRepository<T, Integer> getRepository();

    default T findByID(Integer id) {
        return getRepository().findById(id).orElseThrow();
    }

    default List<T> findAll() {
        return getRepository().findAll();
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default void deleteByID(Integer id) {
        getRepository().deleteById(id);
    }
}
