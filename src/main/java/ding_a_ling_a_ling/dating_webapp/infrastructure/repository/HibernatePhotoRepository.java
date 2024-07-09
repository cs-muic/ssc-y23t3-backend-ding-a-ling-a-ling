package ding_a_ling_a_ling.dating_webapp.infrastructure.repository;

import ding_a_ling_a_ling.dating_webapp.domain.model.photo.PhotoRepository;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernatePhotoRepository extends HibernateSupport<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> implements PhotoRepository {

  HibernatePhotoRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> findPhotos(ProfileId profileId) {
    String sql = "SELECT a.* FROM photo a WHERE a.profile_id = :profileId order by id desc";
    NativeQuery<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> query = getSession().createNativeQuery(sql, ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo.class);
    query.setParameter("profileId", profileId.value());
    return query.list();
  }
}
