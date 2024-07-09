package ding_a_ling_a_ling.dating_webapp.web.updater;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import ding_a_ling_a_ling.dating_webapp.utils.JsonUtils;
import ding_a_ling_a_ling.dating_webapp.web.socket.SubscriptionHub;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileUpdater {

  /**
   * Update the clients when a profile added
   *
   * @param profile the new profile
   */
  public void onProfileAdded(Profile profile) {
    Map<String, Object> profileData = new HashMap<>();
    profileData.put("id", profile.getId().value());
    profileData.put("title", profile.getTitle());

    Map<String, Object> update = new HashMap<>();
    update.put("type", "profileAdded");
    update.put("profile", profileData);

    SubscriptionHub.send("/match/",  JsonUtils.toJson(update));
  }
}
