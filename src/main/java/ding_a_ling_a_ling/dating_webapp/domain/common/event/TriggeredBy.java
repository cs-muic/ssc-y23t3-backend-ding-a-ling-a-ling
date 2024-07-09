package ding_a_ling_a_ling.dating_webapp.domain.common.event;

import ding_a_ling_a_ling.dating_webapp.domain.model.user.UserId;
import ding_a_ling_a_ling.dating_webapp.utils.IpAddress;

public interface TriggeredBy {

  /**
   * Get the id of the user who triggered this command
   *
   * @return a user's id
   */
  UserId getUserId();

  /**
   * Get the IP address where the request originated from
   *
   * @return an IP address
   */
  IpAddress getIpAddress();
}
