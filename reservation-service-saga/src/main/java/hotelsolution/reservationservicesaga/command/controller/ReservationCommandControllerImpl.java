package hotelsolution.reservationservicesaga.command.controller;

import hotelsolution.reservationservicesaga.request.CreateBookingRequest;
import hotelsolution.reservationservicesaga.service.ReservationCommandService;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@Slf4j(topic = "[ReservationControllerImpl]")
@RestController
public class ReservationCommandControllerImpl implements ReservationCommandController {

  private ReservationCommandService reservationCommandService;

  @Autowired
  public ReservationCommandControllerImpl(ReservationCommandService reservationCommandService) {
    this.reservationCommandService = reservationCommandService;
  }

  @Override
  public CompletableFuture<String> createBooking(CreateBookingRequest request) {

    log.info("Create booking request received");
    return reservationCommandService.createBooking(request);
  }

}
