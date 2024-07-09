package ding_a_ling_a_ling.dating_webapp.infrastructure.repository;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateProfileRepository extends HibernateSupport<Profile> implements ProfileRepository {

  private JdbcTemplate jdbcTemplate;

  HibernateProfileRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    super(entityManager);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Profile findById(ProfileId profileId) {
    return getSession().find(Profile.class, profileId.value());
  }

}
