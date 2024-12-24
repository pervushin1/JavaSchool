package sbp.school.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import sbp.school.kafka.config.KafkaConfig;
import sbp.school.kafka.model.Operation;

import java.util.Properties;


public class ProducerService {

    private final Properties properties;

    private final String TOPIC_NAME = "test-topic";


    public ProducerService() {
        properties = KafkaConfig.getKafkaProperties();
    }

    public void send(Operation operation) {
        try (KafkaProducer<String, Operation> producer = new KafkaProducer<>(properties)) {
            producer.send(new ProducerRecord<>(TOPIC_NAME, operation.getType(), operation), (metadata, exception) -> {
                if (exception != null) {
                    System.out.println(exception.getMessage());
                } else {
                    System.out.println("Partition: " + metadata.partition());
                    System.out.println("Offset: " + metadata.offset());
                }
            });
            producer.flush();
        }
    }
}
