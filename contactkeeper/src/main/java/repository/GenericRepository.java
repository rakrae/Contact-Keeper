package repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;

import database.PersistenceManager;

public abstract class GenericRepository<T, ID> implements CrudRepository<T, ID> {

    private final Class<T> entityType;

    public GenericRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    protected EntityManager getEntityManager() {
        return PersistenceManager.getEntityManagerFactory().createEntityManager();
    }


    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        T entity = em.find(entityType, id);
        et.commit();

        em.close();
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        List<T> entities = em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType).getResultList();
        et.commit();

        em.close();
        return entities;
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();
        
        String idFieldName = getIdFieldName(entityType);

        et.begin();
        List<T> entities = em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e WHERE e." + idFieldName + " IN :ids", entityType)
                .setParameter("ids", ids)
                .getResultList();
        et.commit();

        em.close();
        return entities;
    }

    @Override
    public long count() {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        long count = (long) em.createQuery("SELECT COUNT(e) FROM " + entityType.getSimpleName() + " e").getSingleResult();
        et.commit();

        em.close();
        return count;
    }

    @Override
    public void deleteById(ID id) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        T entity = em.find(entityType, id);
        if (entity != null) {
            em.remove(entity);
        }
        et.commit();

        em.close();
    }

    @Override
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        et.commit();

        em.close();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        for (T entity : entities) {
            if (!em.contains(entity)) {
                entity = em.merge(entity);
            }
            em.remove(entity);
        }
        et.commit();

        em.close();
    }

    @Override
    public boolean existsById(ID id) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        String idFieldName = getIdFieldName(entityType);
        
        et.begin();
        boolean exists = em.createQuery("SELECT COUNT(e) FROM " + entityType.getSimpleName() + " e WHERE e." + idFieldName + " = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
        et.commit();

        em.close();
        return exists;
    }
    
    @Override
    public <S extends T> S save(S entity) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(entity);
        et.commit();

        em.close();
        return entity;
    }
    
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        for (S entity : entities) {
            em.persist(entity);
        }
        et.commit();

        em.close();
        return (List<S>) entities;
    }
    
    @Override
    public <S extends T> S update(S entity) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        S mergedEntity = em.merge(entity);
        et.commit();

        em.close();
        return mergedEntity;
    }
    
    private String getIdFieldName(Class<T> entityType) {
        for (Field field : entityType.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getName();
            }
        }
        throw new IllegalArgumentException("Entity class " + entityType.getSimpleName() + " does not have an @Id field.");
    }
}