package com.hackathon.hopi.service.impl;

import com.hackathon.hopi.dao.GenericDao;
import com.hackathon.hopi.service.GenericManager;
import java.io.Serializable;
import java.util.List;

public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
    protected GenericDao<T, PK> dao;

    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public T get(PK id) {
        return dao.get(id);
    }

    public boolean exists(PK id) {
        return dao.exists(id);
    }

    public T save(T object) {
        return dao.save(object);
    }

    public void remove(T object) {
        dao.remove(object);
    }

    public void remove(PK id) {
        dao.remove(id);
    }
}

