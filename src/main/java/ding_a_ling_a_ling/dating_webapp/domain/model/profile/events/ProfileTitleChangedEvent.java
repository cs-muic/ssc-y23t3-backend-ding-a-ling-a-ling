package ding_a_ling_a_ling.dating_webapp.domain.model.profile.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredBy;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;

public class ProfileTitleChangedEvent extends ProfileDomainEvent {

  private static final long serialVersionUID = 26551114425630902L;

  private String newTitle;
  private String oldTitle;

  public ProfileTitleChangedEvent(Profile profile, String oldTitle, TriggeredBy triggeredBy) {
    super(profile.getId(), profile.getTitle(), triggeredBy);
    this.newTitle = profile.getTitle();
    this.oldTitle = oldTitle;
  }

  public String getNewTitle() {
    return newTitle;
  }

  public String getOldTitle() {
    return oldTitle;
  }

  @Override
  public String toString() {
    return "ProfileTitleChangedEvent{" +
      "profileId=" + getProfileId() +
      ", newTitle='" + newTitle + '\'' +
      ", oldTitle='" + oldTitle + '\'' +
      '}';
  }
}
