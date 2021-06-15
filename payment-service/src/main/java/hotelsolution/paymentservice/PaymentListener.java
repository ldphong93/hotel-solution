package hotelsolution.paymentservice;

import hotelsolution.paymentservice.model.PaymentRequest;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j(topic = "[PaymentListener]")
@Component
public class PaymentListener {

    private CountDownLatch greetingLatch = new CountDownLatch(1);

    @Autowired
    private MockPaymentService mockPaymentService;


    @KafkaListener(topics = "payTopic", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(PaymentRequest request) {
        log.info("Received payment request [{}]", request.toString());

        mockPaymentService.doPayment(request.getBookingId(), request.getPaymentId());
        this.greetingLatch.countDown();
    }

}
