package ding_a_ling_a_ling.dating_webapp.domain.application.commands;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

public class ChangeProfileDescriptionCommand extends UserCommand {

  private ProfileId profileId;
  private String description;

  public ChangeProfileDescriptionCommand(ProfileId profileId, String description) {
    this.profileId = profileId;
    this.description = description;
  }

  public ProfileId getProfileId() {
    return profileId;
  }

  public String getDescription() {
    return description;
  }
}
