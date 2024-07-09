package ding_a_ling_a_ling.dating_webapp.web.apis;

import ding_a_ling_a_ling.dating_webapp.domain.application.commands.AnonymousCommand;
import ding_a_ling_a_ling.dating_webapp.domain.application.commands.UserCommand;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.SimpleUser;
import ding_a_ling_a_ling.dating_webapp.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController {

  void addTriggeredBy(UserCommand command, HttpServletRequest request) {
    Assert.notNull(request.getUserPrincipal(), "User principal must be present in the request");
    UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
    SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
    command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
  }

  void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
    command.triggeredBy(RequestUtils.getIpAddress(request));
  }
}
