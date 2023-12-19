package com.example.university.service;


import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
    Optional<T> findById(long id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(long id);
}
