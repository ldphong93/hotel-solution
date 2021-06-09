package hotelsolution.guestserviceaxon.query.service;

import hotelsolution.guestserviceaxon.common.event.GuestCreatedEvent;
import hotelsolution.guestserviceaxon.query.entity.Guest;
import hotelsolution.guestserviceaxon.query.query.FindGuestByIdQuery;
import hotelsolution.guestserviceaxon.query.repository.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[MangeGuestService]")
public class MangeGuestService {

  private final GuestRepository guestRepository;

  public MangeGuestService(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @EventHandler
  public void on(GuestCreatedEvent guestCreatedEvent) {
    log.info("Handling GuestCreatedEvent.");

    guestRepository.save(Guest.builder()
        .id(guestCreatedEvent.getId())
        .userName(guestCreatedEvent.getUserName())
        .password(guestCreatedEvent.getPassword())
        .name(guestCreatedEvent.getName())
        .phoneNumber(guestCreatedEvent.getPhoneNumber())
        .build());
  }

  @EventHandler
  public Guest handle(FindGuestByIdQuery query) {
    log.info("Handling FindGuestByIdQuery.");

    return guestRepository.findById(query.getId()).orElse(null);
  }
}
