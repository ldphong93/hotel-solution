package hotelsolution.guestserviceaxon.query.service;

import hotelsolution.guestserviceaxon.common.event.GuestCreatedEvent;
import hotelsolution.guestserviceaxon.common.event.GuestDeletedEvent;
import hotelsolution.guestserviceaxon.common.event.GuestEditedEvent;
import hotelsolution.guestserviceaxon.query.entity.Guest;
import hotelsolution.guestserviceaxon.query.query.FindGuestByIdQuery;
import hotelsolution.guestserviceaxon.query.repository.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[MangeGuestService]")
public class GuestEventService {

  private final GuestRepository guestRepository;

  public GuestEventService(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @EventHandler
  public void on(GuestCreatedEvent event) {
    log.info("Handling GuestCreatedEvent.");
    guestRepository.save(Guest.builder()
        .id(event.getId())
        .userName(event.getUserName())
        .password(event.getPassword())
        .name(event.getName())
        .phoneNumber(event.getPhoneNumber())
        .build());
  }

  @EventHandler
  public void on(GuestEditedEvent guestEditedEvent) {
    log.info("Handling GuestEditedEvent.");

    Guest guest = guestRepository.findById(guestEditedEvent.getId()).orElse(null);

    if (guest != null) {
      guest.setName(guestEditedEvent.getName());
      guest.setPhoneNumber(guestEditedEvent.getPhoneNumber());
    }
  }

  @EventHandler
  public void on(GuestDeletedEvent guestDeletedEvent) {
    log.info("Handling GuestDeletedEvent.");

    Guest guest = guestRepository.findById(guestDeletedEvent.getId()).orElse(null);

    if (guest != null) {
      guestRepository.delete(guest);
    }
  }

  @QueryHandler
  public Guest handle(FindGuestByIdQuery query) {
    log.info("Handling FindGuestByIdQuery.");

    return guestRepository.findById(query.getId()).orElse(null);
  }
}
