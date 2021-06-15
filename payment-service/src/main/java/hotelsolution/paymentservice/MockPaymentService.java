package hotelsolution.paymentservice;

import hotelsolution.paymentservice.model.PaymentInvoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j(topic = "[MockPaymentService]")
@Service
public class MockPaymentService {

  private String TOPIC_NAME = "invoiceTopic";

  @Autowired
  private KafkaTemplate<String, PaymentInvoice> template;

  public void doPayment(String bookingId, String paymentId) {
    log.info("Payment proceeding...");

    PaymentInvoice invoice = PaymentInvoice.builder()
        .bookingId(bookingId)
        .paymentId(paymentId)
        .paidId("mockPaidId")
        .build();

    Message<PaymentInvoice> message = MessageBuilder
        .withPayload(invoice)
        .setHeader(KafkaHeaders.TOPIC,TOPIC_NAME )
        .build();
    template.send(message);
    log.info("Invoice sent to client.");
  }
}
