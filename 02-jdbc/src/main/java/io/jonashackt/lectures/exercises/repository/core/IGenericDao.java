package io.jonashackt.lectures.exercises.repository.core;


import java.util.Collection;
import java.util.List;

public interface IGenericDao<T> {
    Collection<T> findAll();

    void create(T person);

    T update(T person);

    T findById( Long id );

    void delete(Long id );

    void delete( T entity );

     void delete( List<T> entries );




}
