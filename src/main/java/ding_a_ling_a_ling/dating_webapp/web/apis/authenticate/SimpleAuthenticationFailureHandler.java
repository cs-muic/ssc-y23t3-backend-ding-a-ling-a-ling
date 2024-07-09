package ding_a_ling_a_ling.dating_webapp.web.apis.authenticate;

import ding_a_ling_a_ling.dating_webapp.utils.JsonUtils;
import ding_a_ling_a_ling.dating_webapp.web.results.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    ApiResult failure;
    if (exception instanceof BadCredentialsException) {
      failure = ApiResult.message("Invalid credentials");
    } else if (exception instanceof InsufficientAuthenticationException) {
      failure = ApiResult.message("Invalid authentication request");
    } else {
      failure = ApiResult.message("Authentication failure");
    }
    JsonUtils.write(response.getWriter(), failure);
  }
}
