package ding_a_ling_a_ling.dating_webapp.web.payload;

import ding_a_ling_a_ling.dating_webapp.domain.application.commands.AddProfileCommand;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;

public class AddProfilePayload {

  private long id;

  private long userId;
  private String title;

  public AddProfileCommand toCommand() {
    return new AddProfileCommand(new UserId(userId), title);
  }


  public void setId(long id) {
    this.id = id;
  }


  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}

