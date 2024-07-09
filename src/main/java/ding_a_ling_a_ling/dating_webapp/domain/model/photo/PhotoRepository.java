package ding_a_ling_a_ling.dating_webapp.domain.model.photo;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

import java.util.List;

public interface PhotoRepository {

  /**
   * Find profile photos
   *
   * @param profileId the id of the profile
   * @return a list of photo, empty list if none found
   */
  List<Photo> findPhotos(ProfileId profileId);

  /**
   * Save photo
   *
   * @param photo the photo to save
   */
  void save(Photo photo);
}
