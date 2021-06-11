package hotelsolution.reservationservicesaga.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateReservedEvent {

  private String reservedId;
  private String paymentId;
  private String bookingId;
}
