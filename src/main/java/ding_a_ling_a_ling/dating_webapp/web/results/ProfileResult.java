package ding_a_ling_a_ling.dating_webapp.web.results;

import ding_a_ling_a_ling.dating_webapp.domain.model.profile.Profile;
import org.springframework.http.ResponseEntity;

public class ProfileResult {

  public static ResponseEntity<ApiResult> build(Profile profile) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", profile.getId().value())
      .add("title", profile.getTitle())
      .add("description", profile.getDescription());
    return Result.ok(apiResult);
  }

}
