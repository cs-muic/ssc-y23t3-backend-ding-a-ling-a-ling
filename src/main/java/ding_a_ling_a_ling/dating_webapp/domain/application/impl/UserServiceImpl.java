package ding_a_ling_a_ling.dating_webapp.domain.application.impl;

import ding_a_ling_a_ling.dating_webapp.domain.application.UserService;
import ding_a_ling_a_ling.dating_webapp.domain.application.commands.RegisterCommand;
import ding_a_ling_a_ling.dating_webapp.domain.common.event.DomainEventPublisher;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.*;
import ding_a_ling_a_ling.dating_webapp.domain.model.user.events.UserRegisteredEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private RegistrationManagement registrationManagement;
  private DomainEventPublisher domainEventPublisher;
  private UserRepository userRepository;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                         DomainEventPublisher domainEventPublisher,
                         UserRepository userRepository) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("No user found");
    }
    User user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }
    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }
    return new SimpleUser(user);
  }

  @Override
  public User findById(UserId userId) {
    return userRepository.findById(userId);
  }

  @Override
  public void register(RegisterCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getFirstName(),
      command.getLastName(),
      command.getPassword());

    domainEventPublisher.publish(new UserRegisteredEvent(newUser, command));
  }

}
