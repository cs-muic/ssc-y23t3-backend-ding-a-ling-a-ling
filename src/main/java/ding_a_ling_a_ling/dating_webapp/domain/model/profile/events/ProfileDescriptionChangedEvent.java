package ding_a_ling_a_ling.dating_webapp.domain.model.profile.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredBy;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;

public class ProfileDescriptionChangedEvent extends ProfileDomainEvent {

  private static final long serialVersionUID = 26551114425630902L;

  private String newDescription;
  private String oldDescription;

  public ProfileDescriptionChangedEvent(Profile profile, String oldDescription, TriggeredBy triggeredBy) {
    super(profile.getId(), profile.getTitle(), triggeredBy);
    this.newDescription = profile.getDescription();
    this.oldDescription = oldDescription;
  }

  public String getNewDescription() {
    return newDescription;
  }

  public String getOldDescription() {
    return oldDescription;
  }

  @Override
  public String toString() {
    return "ProfileDescriptionChangedEvent{" +
      "profileId=" + getProfileId() +
      ", newDescription='" + newDescription + '\'' +
      ", oldDescription='" + oldDescription + '\'' +
      '}';
  }
}
