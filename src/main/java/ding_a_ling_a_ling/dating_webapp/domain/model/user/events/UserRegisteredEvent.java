package ding_a_ling_a_ling.dating_webapp.domain.model.user.events;

import ding_a_ling_a_ling.dating_webapp.domain.common.event.DomainEvent;
import ding_a_ling_a_ling.dating_webapp.domain.common.event.TriggeredFrom;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.User;

public class UserRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = 2580061707540917880L;

  public UserRegisteredEvent(User user, TriggeredFrom triggeredFrom) {
    super(user.getId(), triggeredFrom);
  }

  @Override
  public String toString() {
    return "UserRegisteredEvent{userId=" + getUserId() + '}';
  }
}
