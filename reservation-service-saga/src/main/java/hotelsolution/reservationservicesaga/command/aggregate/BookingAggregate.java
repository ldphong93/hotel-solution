package hotelsolution.reservationservicesaga.command.aggregate;

import hotelsolution.reservationservicesaga.command.command.CreateBookingCommand;
import hotelsolution.reservationservicesaga.command.command.CreatePaymentCommand;
import hotelsolution.reservationservicesaga.enums.BookingStatus;
import hotelsolution.reservationservicesaga.event.CreateBookingEvent;
import hotelsolution.reservationservicesaga.event.CreatePaymentEvent;
import hotelsolution.reservationservicesaga.request.PaymentInvoice;
import hotelsolution.reservationservicesaga.request.PaymentRequest;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j(topic = "[BookingAggregate]")
@Aggregate
public class BookingAggregate {

  @AggregateIdentifier
  private String bookingId;
  private String hotelId;
  private String guestId;
  private String roomId;
  private BookingStatus bookingStatus;

  private CountDownLatch countDownLatch = new CountDownLatch(1);

  @Autowired
  private KafkaTemplate<String, PaymentRequest> template;
  private String topicName = "payTopic";

  public BookingAggregate() {
  }

  @CommandHandler
  public BookingAggregate(CreateBookingCommand command) {
    log.info("CreateBookingCommand received.");
    AggregateLifecycle.apply(new CreateBookingEvent(
        command.getBookingId(),
        command.getHotelId(),
        command.getGuestId(),
        command.getRoomId(),
        command.getBookingStatus()));
  }

  @EventSourcingHandler
  public void on(CreateBookingEvent event) {
    log.info("An CreateBookingEvent occurred.");
    this.bookingId = event.getBookingId();
    this.hotelId = event.getHotelId();
    this.guestId = event.getGuestId();
    this.roomId = event.getRoomId();
    this.bookingStatus = event.getBookingStatus();
  }

  @CommandHandler
  public void on(CreatePaymentCommand command) {
    log.info("CreatePaymentCommand received.");
/*    AggregateLifecycle.apply(new CreateBookingEvent(
        command.getBookingId(),
        command.getHotelId(),
        command.getGuestId(),
        command.getRoomId(),
        command.getBookingStatus()));*/

    PaymentRequest paymentRequest = PaymentRequest.builder()
        .bookingId(command.getBookingId())
        .paymentId(command.getPaymentId())
        .build();

    Message<PaymentRequest> message = MessageBuilder
        .withPayload(paymentRequest)
        .setHeader(KafkaHeaders.TOPIC, topicName)
        .build();
    template.send(message);
  }

  @KafkaListener(topics = "invoiceTopic", containerFactory = "paymentKafkaListenerContainerFactory")
  public void invoiceListener(PaymentInvoice invoice) {
    log.info("Received payment invoice [{}]", invoice.toString());
    this.countDownLatch.countDown();

    AggregateLifecycle.apply(new CreatePaymentEvent(
        invoice.getBookingId(),
        invoice.getPaymentId(),
        invoice.getPaidId()));
  }

  @EventSourcingHandler
  public void on(CreatePaymentEvent event) {
    log.info("An CreatePaymentEvent occurred.");
    this.bookingId = event.getBookingId();
  }
}
