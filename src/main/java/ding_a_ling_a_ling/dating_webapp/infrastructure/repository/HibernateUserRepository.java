package ding_a_ling_a_ling.dating_webapp.infrastructure.repository;

import ding_a_ling_a_ling.dating_webapp.domain.model.user.User;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class HibernateUserRepository extends HibernateSupport<User> implements UserRepository {

  public HibernateUserRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public User findByUsername(String username) {
    Query<User> query = getSession().createQuery("from User where username = :username", User.class);
    query.setParameter("username", username);
    return query.uniqueResult();
  }

  @Override
  public User findByEmailAddress(String emailAddress) {
    Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
    query.setParameter("emailAddress", emailAddress);
    return query.uniqueResult();
  }

  @Override
  public User findById(UserId userId) {
    Query<User> query = getSession().createQuery("from User where id = :id", User.class);
    query.setParameter("id", userId.value());
    return query.uniqueResult();
  }
}
