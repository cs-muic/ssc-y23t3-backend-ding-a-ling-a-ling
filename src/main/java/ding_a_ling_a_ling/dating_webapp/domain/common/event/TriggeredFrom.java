package ding_a_ling_a_ling.dating_webapp.domain.common.event;

import ding_a_ling_a_ling.dating_webapp.utils.IpAddress;

public interface TriggeredFrom {

  /**
   * Get the IP address where the request originated from
   *
   * @return an IP address
   */
  IpAddress getIpAddress();
}
