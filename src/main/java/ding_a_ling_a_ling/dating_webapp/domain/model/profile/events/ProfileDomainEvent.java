package ding_a_ling_a_ling.dating_webapp.domain.model.profile.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.DomainEvent;
import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredBy;
import ding_a_ling_a_ling.dating_webapp.domain.model.profile.ProfileId;

public abstract class ProfileDomainEvent extends DomainEvent {

  private static final long serialVersionUID = 8301463735426628027L;

  private ProfileId profileId;
  private String profileTitle;

  public ProfileDomainEvent(ProfileId profileId, String profileTitle, TriggeredBy triggeredBy) {
    super(triggeredBy);
    this.profileId = profileId;
    this.profileTitle = profileTitle;
  }

  public ProfileId getProfileId() {
    return profileId;
  }

  public String getProfileTitle() {
    return profileTitle;
  }

}

