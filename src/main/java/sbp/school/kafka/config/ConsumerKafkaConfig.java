package sbp.school.kafka.config;

import java.util.Properties;

public class ConsumerKafkaConfig {

    private final Properties properties = new Properties();

    public ConsumerKafkaConfig(String valueDeserializer) {
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", valueDeserializer);
        properties.put("group.id", "kafka-consumer");
        properties.put("enable.auto.commit", "false");
        properties.put("fetch.min.bytes", "10");

    }

    public Properties getProperties() {
        return properties;
    }
}
