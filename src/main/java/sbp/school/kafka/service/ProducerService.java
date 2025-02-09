package sbp.school.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import sbp.school.kafka.config.ProducerKafkaConfig;
import sbp.school.kafka.model.Operation;

import static sbp.school.kafka.serializer.SerializerType.OPERATION;
import static sbp.school.kafka.serializer.SerializerType.STRING;


public class ProducerService {

    private final String MAIN_TOPIC = "main_topic";

    private final String CONFIRMATION_TOPIC = "confirmation_topic";

    private ProducerKafkaConfig kafkaConfig;


    public boolean send(Operation operation) {
        kafkaConfig = new ProducerKafkaConfig(OPERATION.getValue());
        boolean isSuccess = false;
        try (KafkaProducer<String, Operation> producer = new KafkaProducer<>(kafkaConfig.getProperties())) {
            producer.send(new ProducerRecord<>(MAIN_TOPIC, operation.getType(), operation), (metadata, exception) -> {
                if (exception != null) {
                    System.out.println("Main topic exception: " + exception.getMessage());
                } else {
                    System.out.printf("Producer: %s, Partition: %s, Offset: %s \n", metadata.topic(), metadata.partition(), metadata.offset());
                }
            });
            producer.flush();
            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
            return isSuccess;
        }
    }

    public String sendConfirmation(Operation operation, boolean isSuccess) {
        // ваш топик для подтверждений
        kafkaConfig = new ProducerKafkaConfig(STRING.getValue());
        String confirmationKey = "id: " + operation.getId();
        String confirmationValue = isSuccess ? "SUCCESS" : "FAILURE";
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaConfig.getProperties())) {
            producer.send(new ProducerRecord<>(CONFIRMATION_TOPIC, confirmationKey, confirmationValue), (metadata, exception) -> {
                if (exception != null) {
                    System.out.println("Confirmation topic exception: " + exception.getMessage());
                } else {
                    System.out.printf("Producer: %s, Partition: %s, Offset: %s \n", metadata.topic(), metadata.partition(), metadata.offset());
                }
            });
            producer.flush();
        }
        return confirmationKey;
    }
}
