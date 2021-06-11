package hotelsolution.reservationservicesaga.command.command;

import hotelsolution.reservationservicesaga.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class CreateBookingCommand {

  @TargetAggregateIdentifier
  private String bookingId;
  private String hotelId;
  private String guestId;
  private String roomId;
  private BookingStatus bookingStatus;
}
