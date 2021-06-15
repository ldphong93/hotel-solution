package hotelsolution.reservationservicesaga.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInvoice {

  private String paidId;
  private String bookingId;
  private String paymentId;
}
