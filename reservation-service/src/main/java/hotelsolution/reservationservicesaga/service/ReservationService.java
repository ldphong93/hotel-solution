package hotelsolution.reservationservicesaga.service;

import hotelsolution.reservationservicesaga.model.dto.HotelDto;
import hotelsolution.reservationservicesaga.model.dto.ReservationDto;
import hotelsolution.reservationservicesaga.model.dto.RoomDto;
import hotelsolution.reservationservicesaga.request.CreateReservationRequest;
import java.math.BigInteger;
import java.util.List;

public interface ReservationService {

  HotelDto findHotelByName(String hotelName);

  List<HotelDto> findAllHotelByCity(String hotelCity);

  List<RoomDto> findAllRoomByHotelId(BigInteger hotelId);

  ReservationDto create(CreateReservationRequest request);

  List<ReservationDto> findAllReservationByGuestId(BigInteger guestId);

}
