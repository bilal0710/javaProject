package io.jonashackt.lectures.exercises.repository.core;

import java.util.Collection;

public interface IGenericDao<T> {
    Collection<T> findAll();

    void create(T person);

    void update(T person);




}
