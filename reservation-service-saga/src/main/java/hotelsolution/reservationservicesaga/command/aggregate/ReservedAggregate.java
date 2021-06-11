package hotelsolution.reservationservicesaga.command.aggregate;

import hotelsolution.reservationservicesaga.command.command.CreateReservedCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.event.CreateReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class ReservedAggregate {

  @AggregateIdentifier
  private String reservedId;
  private String paymentId;
  private BookingStatus status;

  public ReservedAggregate() {
  }

  @CommandHandler
  public ReservedAggregate(CreateReservedCommand command) {
    log.info("CreateReservedCommand received.");
    AggregateLifecycle
        .apply(new CreateReservedEvent(command.getReservedId(), command.getPaymentId(),
            command.getBookingId()));
  }

  @EventSourcingHandler
  public void on(CreateReservedEvent event) {
    log.info("An CreateReservedEvent occurred.");
    this.reservedId = event.getReservedId();
    this.paymentId = event.getPaymentId();
    this.status = BookingStatus.RESERVED;
  }
}
