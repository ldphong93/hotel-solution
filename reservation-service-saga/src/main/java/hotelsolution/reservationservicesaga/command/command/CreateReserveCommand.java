package hotelsolution.reservationservicesaga.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class CreateReserveCommand {

  @TargetAggregateIdentifier
  private String bookingId;
  private String paymentId;
  private String paidId;
}