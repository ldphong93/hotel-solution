package hotelsolution.reservationservicesaga.saga;

import hotelsolution.reservationservicesaga.command.command.CreatePaymentCommand;
import hotelsolution.reservationservicesaga.command.command.CreateReservedCommand;
import hotelsolution.reservationservicesaga.event.CreateBookingEvent;
import hotelsolution.reservationservicesaga.event.CreatePaymentEvent;
import hotelsolution.reservationservicesaga.event.CreateReservedEvent;
import hotelsolution.reservationservicesaga.request.PaymentRequest;
import java.util.UUID;
import javax.inject.Inject;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Saga
public class BookingManagementSaga {

  @Autowired
  private KafkaTemplate<String, PaymentRequest> template;

  @Inject
  private transient CommandGateway commandGateway;

  private String topicName = "payTopic";


  @StartSaga
  @SagaEventHandler(associationProperty = "bookingId")
  public void handle(CreateBookingEvent createBookingEvent) {
    String paymentId = UUID.randomUUID().toString();
    System.out.println("Saga invoked.");

    // associate saga
    SagaLifecycle.associateWith("paymentId", paymentId);

    System.out.println("Booking id " + createBookingEvent.getBookingId());

    //send the command
    commandGateway.send(new CreatePaymentCommand(paymentId, createBookingEvent.getBookingId()));
  }

  @SagaEventHandler(associationProperty = "paymentId")
  public void handle(CreatePaymentEvent event) {
    String reservedId = UUID.randomUUID().toString();
    System.out.println("Saga continue.");

    //associate saga
    SagaLifecycle.associateWith("reservedId", reservedId);

    System.out.println("Payment id " + event.getPaymentId());

    //send command
    commandGateway
        .send(new CreateReservedCommand(reservedId, event.getPaymentId(), event.getBookingId()));

//    PaymentRequest paymentRequest = PaymentRequest.builder()
//        .bookingId(event.getBookingId())
//        .paymentId(event.getPaymentId())
//        .build();
//
//    Message<PaymentRequest> message = MessageBuilder
//        .withPayload(paymentRequest)
//        .setHeader(KafkaHeaders.TOPIC,topicName )
//        .build();
//    template.send(message);
  }

  @SagaEventHandler(associationProperty = "reservedId")
  public void handle(CreateReservedEvent event) {
    SagaLifecycle.end();
  }
}
