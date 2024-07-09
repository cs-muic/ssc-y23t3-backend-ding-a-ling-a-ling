package ding_a_ling_a_ling.dating_webapp.web.payload;

import ding_a_ling_a_ling.dating_webapp.domain.application.commands.ChangeProfileTitleCommand;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

public class ChangeProfileTitlePayload {

  private String title;

  public ChangeProfileTitleCommand toCommand(long profileId) {
    return new ChangeProfileTitleCommand(new ProfileId(profileId), title);
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
