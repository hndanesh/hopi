package com.hackathon.hopi.dao.jpa;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.hackathon.hopi.dao.GenericDao;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class GenericDaoJpa<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	@PersistenceContext
	private EntityManager entityManager;
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	private Class<T> persistentClass;

	public GenericDaoJpa(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public GenericDaoJpa(final Class<T> persistentClass,
			EntityManager entityManager) {
		this.persistentClass = persistentClass;
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return this.entityManager.createQuery(
				"select obj from " + this.persistentClass.getName() + " obj")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection result = new LinkedHashSet(getAll());
		return new ArrayList(result);
	}

	@Transactional
	public T get(PK id) {
		T entity = this.entityManager.find(this.persistentClass, id);

		if (entity == null) {
			String msg = this.persistentClass
					+ "' object with id '" + id + "' not found...";
			throw new EntityNotFoundException(msg);
		}

		return entity;
	}

	public boolean exists(PK id) {
		T entity = this.entityManager.find(this.persistentClass, id);
		return entity != null;
	}

	@Transactional
	public T save(T object) {
		return this.entityManager.merge(object);
	}

	public void remove(T object) {
		this.entityManager.remove(object);
	}

	public void remove(PK id) {
		this.entityManager.remove(this.get(id));
	}
}
