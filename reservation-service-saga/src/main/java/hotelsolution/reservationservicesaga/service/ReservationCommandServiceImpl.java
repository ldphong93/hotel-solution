package hotelsolution.reservationservicesaga.service;

import hotelsolution.reservationservicesaga.command.command.CreateBookingCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.request.CreateBookingRequest;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j(topic = "[ReservationServiceImpl]")
@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

  private final CommandGateway commandGateway;

  @Autowired
  public ReservationCommandServiceImpl(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @Override
  public CompletableFuture<String> createBooking(CreateBookingRequest request) {

    return commandGateway.send(
        new CreateBookingCommand(UUID.randomUUID().toString(), request.getHotelId(),
            request.getGuestId(), request.getRoomId(),
            BookingStatus.CREATED));
  }
}
