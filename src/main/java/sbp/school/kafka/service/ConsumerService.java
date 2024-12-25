package sbp.school.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import sbp.school.kafka.configuration.KafkaConfig;
import sbp.school.kafka.model.Operation;

import java.time.Duration;
import java.util.List;

public class ConsumerService {

    private final KafkaConfig kafkaConfig = new KafkaConfig();
    private final KafkaConsumer<String, Operation> kafkaConsumer = new KafkaConsumer<>(kafkaConfig.getProperties());

    public void listen(List<String> topics) {

        kafkaConsumer.subscribe(topics);

        while (true) {

            ConsumerRecords<String, Operation> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100L));
            for (ConsumerRecord<String, Operation> consumerRecord : consumerRecords) {
                System.out.printf("Topic: %s Partition: %s Offset: %s Value: %s",
                        consumerRecord.topic(),
                        consumerRecord.partition(),
                        consumerRecord.offset(),
                        consumerRecord.value());
            }
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.out.println("CommitSync failed " + e.getMessage());
            }
        }


    }

}
