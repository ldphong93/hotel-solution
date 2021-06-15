package hotelsolution.reservationservicesaga.service;

import hotelsolution.reservationservicesaga.request.CreateBookingRequest;
import java.util.concurrent.CompletableFuture;

public interface ReservationCommandService {

  CompletableFuture<String> createBooking(CreateBookingRequest request);

}
