package sbp.school.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import sbp.school.kafka.config.KafkaConfig;

import java.util.Properties;

public class ProducerService {

    private final Properties properties;


    public ProducerService() {
        properties = KafkaConfig.getKafkaProperties();
    }

    public void send() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>("quick-oleg", "products", "Russia"));
        producer.flush();
    }
}
