package ding_a_ling_a_ling.dating_webapp.domain.application.commands;


import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;

public class AddProfileCommand extends UserCommand {

  private ProfileId profileId;
  private UserId userId;
  private String title;

  public AddProfileCommand(UserId userId, String title) {
    this.userId = userId;
    this.title = title;
  }

  public UserId getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }
}
