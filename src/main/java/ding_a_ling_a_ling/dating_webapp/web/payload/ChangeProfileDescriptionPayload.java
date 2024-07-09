package ding_a_ling_a_ling.dating_webapp.web.payload;

import ding_a_ling_a_ling.dating_webapp.domain.application.commands.ChangeProfileDescriptionCommand;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

public class ChangeProfileDescriptionPayload {

  private String description;

  public ChangeProfileDescriptionCommand toCommand(long profileId) {
    return new ChangeProfileDescriptionCommand(new ProfileId(profileId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
