package ding_a_ling_a_ling.dating_webapp.web.results;


import ding_a_ling_a_ling.dating_webapp.domain.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDataResult {

  public static ResponseEntity<ApiResult> build(User user,
                                                String realTimeServerUrl, String realTimeToken) {

    Map<String, Object> userData = new HashMap<>();
    userData.put("name", user.getFirstName() + " " + user.getLastName());
    userData.put("token", realTimeToken);

    Map<String, Object> settings = new HashMap<>();
    settings.put("realTimeServerUrl", realTimeServerUrl);



    ApiResult apiResult = ApiResult.blank()
      .add("user", userData)
      .add("settings", settings);

    return Result.ok(apiResult);
  }

}
