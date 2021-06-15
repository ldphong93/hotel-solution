package hotelsolution.reservationservicesaga.command.controller;

import hotelsolution.reservationservicesaga.request.CreateBookingRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/reservation-axon")
public interface ReservationCommandController {

  @PostMapping("/create")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Reservation created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
      @ApiResponse(responseCode = "404", description = "Can not create reservation.", content = @Content)})
  CompletableFuture<String> createBooking(@Valid @RequestBody CreateBookingRequest request);

}
