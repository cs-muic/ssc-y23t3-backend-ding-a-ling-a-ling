package ding_a_ling_a_ling.dating_webapp.domain.model.profile.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredBy;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;

public class ProfileAddedEvent extends ProfileDomainEvent {

  private static final long serialVersionUID = 26551114425630902L;

  public ProfileAddedEvent(Profile profile, TriggeredBy triggeredBy) {
    super(profile.getId(), profile.getTitle(),  triggeredBy);
  }

  @Override
  public String toString() {
    return "ProfileAddedEvent{" +
      "profileId=" + getProfileId() +
      ", profileTitle='" + getProfileTitle() + '\'' +
      '}';
  }
}
