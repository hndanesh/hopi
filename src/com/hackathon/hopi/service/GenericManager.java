package com.hackathon.hopi.service;

import java.io.Serializable;
import java.util.List;

public interface GenericManager<T, PK extends Serializable> {

    List<T> getAll();

    T get(PK id);

    boolean exists(PK id);

    T save(T object);

    void remove(T object);

    void remove(PK id);

}
