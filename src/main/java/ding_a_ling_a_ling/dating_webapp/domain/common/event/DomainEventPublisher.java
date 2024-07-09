package ding_a_ling_a_ling.dating_webapp.domain.common.event;

public interface DomainEventPublisher {

  /**
   * Publish a domain event
   */
  void publish(DomainEvent event);
}
