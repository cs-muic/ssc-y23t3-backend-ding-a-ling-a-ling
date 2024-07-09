package ding_a_ling_a_ling.dating_webapp.domain.application;

import ding_a_ling_a_ling.dating_webapp.domain.application.commands.*;
import ding_a_ling_a_ling.dating_webapp.domain.model.activity.Activity;
import ding_a_ling_a_ling.dating_webapp.domain.model.board.BoardId;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

import java.util.List;

public interface ProfileService {


  /**
   * Find profile by its id
   *
   * @param profileId the id of the profile
   * @return a profile instance or null if not found
   */
  Profile findById(ProfileId profileId);

  /**
   * Get profile photos
   *
   * @param profileId the id of the profile
   * @return a list of profile photos
   */
  List<ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo> getPhotos(ProfileId profileId);

  /**
   * Add profile
   *
   * @param command the command instance
   * @return the newly added profile
   */
  Profile addProfile(AddProfileCommand command);


  /**
   * Change profile's title
   *
   * @param command the command instance
   */
  void changeProfileTitle(ChangeProfileTitleCommand command);

  /**
   * Change profile's description
   *
   * @param command the command instance
   */
  void changeProfileDescription(ChangeProfileDescriptionCommand command);


  /**
   * Add photo to a profile
   *
   * @param command the command instance
   * @return created photo
   */
  ding_a_ling_a_ling.dating_webapp.domain.model.photo.Photo addPhoto(AddProfilePhotoCommand command);

}
