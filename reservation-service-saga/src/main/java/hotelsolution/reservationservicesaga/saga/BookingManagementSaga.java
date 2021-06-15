package hotelsolution.reservationservicesaga.saga;

import hotelsolution.reservationservicesaga.command.command.CreatePaymentCommand;
import hotelsolution.reservationservicesaga.event.CreateBookingEvent;
import hotelsolution.reservationservicesaga.event.CreateReserveEvent;
import java.util.UUID;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j(topic = "[BookingManagementSaga]")
public class BookingManagementSaga {

  @Inject
  private transient CommandGateway commandGateway;
/*
  @Autowired
  private CommandGateway commandGateway;*/

  @StartSaga
  @SagaEventHandler(associationProperty = "bookingId")
  public void handle(CreateBookingEvent createBookingEvent) {
    log.info("CreateBookingEvent received");
    log.info("Saga invoked.");

    // associate saga
    SagaLifecycle.associateWith("bookingId", createBookingEvent.getBookingId());

    log.info("Booking id [{}]", createBookingEvent.getBookingId());

    String paymentId = UUID.randomUUID().toString();
    //send the command
    commandGateway.send(new CreatePaymentCommand(createBookingEvent.getBookingId(), paymentId));
  }

  @SagaEventHandler(associationProperty = "bookingId")
  public void handle(CreateReserveEvent event) {
    log.info("Saga continue.");

    //associate saga
    SagaLifecycle.associateWith("bookingId", event.getBookingId());

    log.info("Payment id [{}]", event.getPaymentId());
    log.info("Booking id [{}]", event.getBookingId());
    log.info("Paid invoice id [{}]", event.getPaidId());

    log.info("Saga end.");
    SagaLifecycle.end();
  }

}
