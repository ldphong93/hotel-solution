package hotelsolution.reservationservicesaga.exception;

import hotelsolution.reservationservicesaga.enums.ErrorResponse;
import lombok.Getter;

@Getter
public class ReservationServiceException extends RuntimeException{
  private final ErrorResponse errorResponse;

  public ReservationServiceException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
