package sbp.school.kafka.service;

import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import sbp.school.kafka.config.ConsumerKafkaConfig;
import sbp.school.kafka.model.Operation;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ConsumerService {

    public ConcurrentHashMap<String, String> consumerMap = new ConcurrentHashMap<>();

    public void listen(List<String> topics, String deserializer, ChekerService chekerService) {
        ConsumerKafkaConfig kafkaConfig = new ConsumerKafkaConfig(deserializer);
        KafkaConsumer<String, Operation> kafkaConsumer = new KafkaConsumer<>(kafkaConfig.getProperties());

        kafkaConsumer.subscribe(topics);

        while (true) {
            ConsumerRecords<String, Operation> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(10));
            for (ConsumerRecord<String, Operation> consumerRecord : consumerRecords) {
                System.out.printf("Consumer: %s, %s, Status: %s \n",
                        consumerRecord.topic(),
                        consumerRecord.key(),
                        consumerRecord.value());
                if (consumerMap.containsKey(consumerRecord.key())) {
                    consumerMap.put(consumerRecord.key(), consumerRecord.key());
                } else {
                    consumerMap.put("null", consumerRecord.key());
                }
            }
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.out.println("CommitSync failed " + e.getMessage());
            }
        }


    }

}