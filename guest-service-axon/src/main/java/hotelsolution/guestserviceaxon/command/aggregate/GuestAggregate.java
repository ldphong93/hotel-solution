package hotelsolution.guestserviceaxon.command.aggregate;

import hotelsolution.guestserviceaxon.command.command.CreateGuestCommand;
import hotelsolution.guestserviceaxon.common.event.GuestCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
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

  public void on(GuestCreatedEvent guestCreatedEvent) {
    log.info("An GuestCreatedEvent occurred.");
    this.id =guestCreatedEvent.getId();
    this.userName =guestCreatedEvent.getUserName();
    this.password =guestCreatedEvent.getPassword();
    this.name =guestCreatedEvent.getName();
    this.phoneNumber =guestCreatedEvent.getPhoneNumber();
  }


}
