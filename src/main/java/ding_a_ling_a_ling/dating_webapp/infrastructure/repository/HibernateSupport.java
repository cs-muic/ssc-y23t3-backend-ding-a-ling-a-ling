package ding_a_ling_a_ling.dating_webapp.infrastructure.repository;

import org.hibernate.Session;

import jakarta.persistence.EntityManager;

abstract class HibernateSupport<T> {

  private EntityManager entityManager;

  HibernateSupport(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  Session getSession() {
    return entityManager.unwrap(Session.class);
  }

  public void save(T object) {
    entityManager.persist(object);
    entityManager.flush();
  }
}
