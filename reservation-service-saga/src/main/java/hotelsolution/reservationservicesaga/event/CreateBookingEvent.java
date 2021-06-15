package hotelsolution.reservationservicesaga.event;

import hotelsolution.reservationservicesaga.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBookingEvent {

  private String bookingId;
  private String hotelId;
  private String guestId;
  private String roomId;
  private BookingStatus bookingStatus;
}
