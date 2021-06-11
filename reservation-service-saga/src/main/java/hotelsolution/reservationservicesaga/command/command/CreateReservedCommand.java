package hotelsolution.reservationservicesaga.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class CreateReservedCommand {

  @TargetAggregateIdentifier
  private final String reservedId;
  private final String paymentId;
  private final String bookingId;
}
