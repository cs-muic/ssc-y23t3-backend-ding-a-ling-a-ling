package ding_a_ling_a_ling.dating_webapp.web.apis.authenticate;

import ding_a_ling_a_ling.dating_webapp.utils.JsonUtils;
import ding_a_ling_a_ling.dating_webapp.web.results.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleLogoutSuccessHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    throws IOException {

    response.setStatus(HttpStatus.OK.value());
    JsonUtils.write(response.getWriter(), ApiResult.message("logged-out"));
  }
}
