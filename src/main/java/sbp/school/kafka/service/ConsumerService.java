package sbp.school.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import sbp.school.kafka.config.ConsumerKafkaConfig;
import sbp.school.kafka.model.Operation;

import java.time.Duration;
import java.util.List;

public class ConsumerService {


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
                if (chekerService.map.containsKey(consumerRecord.key())) {
                    chekerService.map.put(consumerRecord.key(), consumerRecord.key());
                } else {
                    chekerService.map.put("null", consumerRecord.key());
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