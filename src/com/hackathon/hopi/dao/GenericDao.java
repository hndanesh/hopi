package com.hackathon.hopi.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

    List<T> getAll();

    List<T> getAllDistinct();

    T get(PK id);

    boolean exists(PK id);

    T save(T object);

    void remove(T object);

    void remove(PK id);

}