package hotelsolution.reservationservicesaga.service;

import hotelsolution.reservationservicesaga.command.command.CreateBookingCommand;
import hotelsolution.reservationservicesaga.command.command.CreateReserveCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.request.CreateBookingRequest;
import hotelsolution.reservationservicesaga.request.PaymentInvoice;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j(topic = "[ReservationServiceImpl]")
@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

  private final CommandGateway commandGateway;
  private CountDownLatch countDownLatch = new CountDownLatch(1);

  @Autowired
  public ReservationCommandServiceImpl(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @Override
  public CompletableFuture<String> createBooking(CreateBookingRequest request) {
    log.info("CreateBookingRequest received.");

    return commandGateway.send(
        new CreateBookingCommand(UUID.randomUUID().toString(), request.getHotelId(),
            request.getGuestId(), request.getRoomId(),
            BookingStatus.CREATED));
  }

  @KafkaListener(topics = "invoiceTopic", containerFactory = "paymentKafkaListenerContainerFactory")
  public CompletableFuture<String> invoiceListener(PaymentInvoice invoice) {
    log.info("Received payment invoice [{}]", invoice.toString());
    this.countDownLatch.countDown();

    return commandGateway.send(
        new CreateReserveCommand(invoice.getBookingId(), invoice.getPaymentId(),
            invoice.getPaidId()));
  }
}
