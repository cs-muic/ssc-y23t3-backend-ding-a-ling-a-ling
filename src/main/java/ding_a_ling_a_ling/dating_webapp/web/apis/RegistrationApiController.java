package ding_a_ling_a_ling.dating_webapp.web.apis;

import ding_a_ling_a_ling.dating_webapp.domain.application.UserService;
import ding_a_ling_a_ling.dating_webapp.domain.application.commands.RegisterCommand;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.EmailAddressExistsException;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.RegistrationException;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.UsernameExistsException;
import ding_a_ling_a_ling.dating_webapp.web.payload.RegistrationPayload;
import ding_a_ling_a_ling.dating_webapp.web.results.ApiResult;
import ding_a_ling_a_ling.dating_webapp.web.results.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegistrationApiController extends AbstractBaseController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload,
                                            HttpServletRequest request) {
    try {
      RegisterCommand command = payload.toCommand();
      addTriggeredBy(command, request);

      service.register(command);
      return Result.created();
    } catch (RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistsException) {
        errorMessage = "Username already exists";
      } else if (e instanceof EmailAddressExistsException) {
        errorMessage = "Email address already exists";
      }
      return Result.failure(errorMessage);
    }
  }
}
