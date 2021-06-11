package hotelsolution.reservationservicesaga.command.aggregate;

import hotelsolution.reservationservicesaga.command.command.CreatePaymentCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.event.CreatePaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {

  @AggregateIdentifier
  private String paymentId;

  private String bookingId;

  private BookingStatus status;

  public PaymentAggregate() {
  }

  @CommandHandler
  public PaymentAggregate(CreatePaymentCommand command) {
    log.info("CreatePaymentCommand received.");
    AggregateLifecycle
        .apply(new CreatePaymentEvent(command.getPaymentId(), command.getBookingId()));
  }

  @EventSourcingHandler
  public void on(CreatePaymentEvent event) {
    log.info("An CreatePaymentEvent occurred.");
    this.paymentId = event.getPaymentId();
    this.bookingId = event.getBookingId();
    this.status = BookingStatus.PAID;
  }
}
