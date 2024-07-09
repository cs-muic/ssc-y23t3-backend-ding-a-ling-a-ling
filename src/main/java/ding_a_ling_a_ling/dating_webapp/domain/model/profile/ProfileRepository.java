package ding_a_ling_a_ling.dating_webapp.domain.model.profile;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;



import java.util.List;

public interface ProfileRepository {

  /**
   * Find a profile by its id
   *
   * @param profileId the id of a profile
   * @return the profile instance or null if not found
   */
  Profile findById(ProfileId profileId);



  /**
   * Save profile
   *
   * @param profile the profile to save
   */
  void save(Profile profile);


}
