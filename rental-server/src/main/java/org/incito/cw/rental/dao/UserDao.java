package org.incito.cw.rental.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.incito.cw.rental.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> {

  @PersistenceContext
  private EntityManager entityManager;

  public User find(String id) {
    return entityManager.find(User.class, id);
  }

  public User findByAccount(String account) {
    Query createQuery = entityManager.createQuery("select u from User u where account = ?1");
    createQuery.setParameter(1, account);
    return (User) createQuery.getSingleResult();
  }

  @Override
  public Class<User> getEntityType() {
    return User.class;
  }

  @SuppressWarnings("unchecked")
  public List<User> getUserList() {
    return entityManager.createQuery("select u from User u").getResultList();
  }

}
