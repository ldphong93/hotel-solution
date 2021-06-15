package hotelsolution.reservationservicesaga.request;

import lombok.Data;

@Data
public class CreateBookingRequest {

  private String hotelId;
  private String guestId;
  private String roomId;
}
