package hotelsolution.hotelservice.model.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HotelDto {

  private BigInteger id;
  private String name;
  private String starRating;
}
