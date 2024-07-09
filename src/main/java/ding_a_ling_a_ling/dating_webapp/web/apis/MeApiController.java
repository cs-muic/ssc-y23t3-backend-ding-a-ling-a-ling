package ding_a_ling_a_ling.dating_webapp.web.apis;

import ding_a_ling_a_ling.dating_webapp.domain.application.UserService;
import ding_a_ling_a_ling.dating_webapp.domain.common.security.CurrentUser;
import ding_a_ling_a_ling.dating_webapp.domain.common.security.TokenManager;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.SimpleUser;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.User;
import ding_a_ling_a_ling.dating_webapp.web.results.ApiResult;
import ding_a_ling_a_ling.dating_webapp.web.results.MyDataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeApiController {

  private String realTimeServerUrl;
  private UserService userService;
  private TokenManager tokenManager;

  public MeApiController(@Value("${app.real-time-server-url}") String realTimeServerUrl,
                         UserService userService,
                         TokenManager tokenManager) {
    this.realTimeServerUrl = realTimeServerUrl;
    this.userService = userService;
    this.tokenManager = tokenManager;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    User user = userService.findById(currentUser.getUserId());
    String realTimeToken = tokenManager.jwt(user.getId());
    return MyDataResult.build(user, realTimeServerUrl, realTimeToken);
  }
}
