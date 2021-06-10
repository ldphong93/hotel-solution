package hotelsolution.guestserviceaxon.command.aggregate;

import hotelsolution.guestserviceaxon.command.command.CreateGuestCommand;
import hotelsolution.guestserviceaxon.command.command.DeleteGuestCommand;
import hotelsolution.guestserviceaxon.command.command.EditGuestCommand;
import hotelsolution.guestserviceaxon.common.event.GuestCreatedEvent;
import hotelsolution.guestserviceaxon.common.event.GuestDeletedEvent;
import hotelsolution.guestserviceaxon.common.event.GuestEditedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j(topic = "[GuestAggregate]")
public class GuestAggregate {

  @AggregateIdentifier
  private String id;
  private String userName;
  private String password;
  private String name;
  private String phoneNumber;

  public GuestAggregate() {
  }

  @CommandHandler
  public GuestAggregate(CreateGuestCommand createGuestCommand) {
    log.info("CreateGuestCommand received.");
    AggregateLifecycle.apply(new GuestCreatedEvent(
        createGuestCommand.getId(),
        createGuestCommand.getUserName(),
        createGuestCommand.getPassword(),
        createGuestCommand.getName(),
        createGuestCommand.getPhoneNumber()));
  }

  @EventSourcingHandler
  public void on(GuestCreatedEvent guestCreatedEvent) {
    log.info("An GuestCreatedEvent occurred.");
    this.id = guestCreatedEvent.getId();
    this.userName = guestCreatedEvent.getUserName();
    this.password = guestCreatedEvent.getPassword();
    this.name = guestCreatedEvent.getName();
    this.phoneNumber = guestCreatedEvent.getPhoneNumber();
  }

  @CommandHandler
  public GuestAggregate(EditGuestCommand editGuestCommand) {
    log.info("EditGuestCommand received.");
    AggregateLifecycle.apply(new GuestEditedEvent(
        editGuestCommand.getId(),
        editGuestCommand.getName(),
        editGuestCommand.getPhoneNumber()));
  }

  @EventSourcingHandler
  public void on(GuestEditedEvent guestEditedEvent) {
    log.info("An GuestEditedEvent occurred.");
    this.id = guestEditedEvent.getId();
    this.name = guestEditedEvent.getName();
    this.phoneNumber = guestEditedEvent.getPhoneNumber();
  }

  @CommandHandler
  public GuestAggregate(DeleteGuestCommand deleteGuestCommand) {
    log.info("DeleteGuestCommand received.");
    AggregateLifecycle.apply(new GuestDeletedEvent(deleteGuestCommand.getId()));
  }

  @EventSourcingHandler
  public void on(GuestDeletedEvent guestDeletedEvent) {
    log.info("An GuestDeletedEvent occurred.");
  }


}
