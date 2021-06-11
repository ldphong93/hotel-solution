package hotelsolution.reservationservicesaga.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePaymentEvent {

  private String paymentId;
  private String bookingId;
}
