package ding_a_ling_a_ling.dating_webapp.domain.application.commands;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

public class ChangeProfileTitleCommand extends UserCommand {

  private ProfileId profileId;
  private String title;

  public ChangeProfileTitleCommand(ProfileId profileId, String title) {
    this.profileId = profileId;
    this.title = title;
  }

  public ProfileId getProfileId() {
    return profileId;
  }

  public String getTitle() {
    return title;
  }
}
