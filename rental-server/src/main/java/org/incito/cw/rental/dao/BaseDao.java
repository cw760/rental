package org.incito.cw.rental.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.incito.cw.rental.model.BaseVO;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T extends BaseVO> {

  @PersistenceContext
  private EntityManager entityManager;

  public T find(String id) {
    Class<T> type = getEntityType();
    return entityManager.find(type, id);
  }

  public abstract Class<T> getEntityType();

  @Transactional
  public T save(T entity) {
    Date cd = entity.getCreateDate();
    if (cd == null) {
      cd = new Date();
      entity.setCreateDate(cd);
    }
    entity.setLastModified(new Date());

    if (entity.getId() == null) {
      entityManager.persist(entity);
      return entity;
    } else {
      return entityManager.merge(entity);
    }
  }

}
