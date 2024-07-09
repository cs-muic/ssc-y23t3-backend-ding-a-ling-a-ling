package ding_a_ling_a_ling.dating_webapp.domain.common.event;

import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import ding_a_ling_a_ling.dating_webapp.utils.IpAddress;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain event. It is about who did what at what time.
 */
public abstract class DomainEvent implements Serializable {

  private static final long serialVersionUID = 8945128060450240352L;

  private UserId userId;
  private IpAddress ipAddress;
  private Date occurredAt;

  public DomainEvent(TriggeredBy triggeredBy) {
    this.userId = triggeredBy.getUserId();
    this.ipAddress = triggeredBy.getIpAddress();
    this.occurredAt = new Date();
  }

  public DomainEvent(UserId userId, TriggeredFrom triggeredFrom) {
    this.userId = userId;
    this.ipAddress = triggeredFrom.getIpAddress();
    this.occurredAt = new Date();
  }

  public UserId getUserId() {
    return userId;
  }

  public IpAddress getIpAddress() {
    return ipAddress;
  }

  public Date getOccurredAt() {
    return occurredAt;
  }
}
