package hotelsolution.reservationservicesaga.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class CreatePaymentCommand {

  @TargetAggregateIdentifier
  private String bookingId;
  private String paymentId;



}
