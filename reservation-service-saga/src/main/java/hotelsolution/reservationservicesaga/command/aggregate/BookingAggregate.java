package hotelsolution.reservationservicesaga.command.aggregate;

import hotelsolution.reservationservicesaga.command.command.CreateBookingCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.event.CreateBookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class BookingAggregate {

  @AggregateIdentifier
  private String bookingId;
  private String hotelId;
  private String guestId;
  private String roomId;
  private BookingStatus bookingStatus;

  public BookingAggregate() {
  }

  @CommandHandler
  public BookingAggregate(CreateBookingCommand command) {
    log.info("CreateBookingCommand received.");
    AggregateLifecycle.apply(new CreateBookingEvent(
        command.getBookingId(),
        command.getHotelId(),
        command.getGuestId(),
        command.getRoomId(),
        command.getBookingStatus()));
  }

  @EventSourcingHandler
  public void on(CreateBookingEvent event) {
    log.info("An CreateBookingEvent occurred.");
    this.bookingId = event.getBookingId();
    this.hotelId = event.getHotelId();
    this.guestId = event.getGuestId();
    this.roomId = event.getRoomId();
    this.bookingStatus = event.getBookingStatus();
  }

}
