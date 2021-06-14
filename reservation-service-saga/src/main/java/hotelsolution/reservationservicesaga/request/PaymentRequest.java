package hotelsolution.reservationservicesaga.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequest {

  private String bookingId;
  private String paymentId;
}
