package hotelsolution.reservationservicesaga.config;

import hotelsolution.reservationservicesaga.request.PaymentInvoice;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  private String bootstrapAddress = "localhost:29092";

  public ConsumerFactory<String, PaymentInvoice> greetingConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "invoiceTopic");
    JsonDeserializer<PaymentInvoice> customDeserializer = new JsonDeserializer<>(PaymentInvoice.class);
    customDeserializer.setUseTypeMapperForKey(true);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), customDeserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, PaymentInvoice> paymentKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, PaymentInvoice> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(greetingConsumerFactory());
    return factory;
  }
}