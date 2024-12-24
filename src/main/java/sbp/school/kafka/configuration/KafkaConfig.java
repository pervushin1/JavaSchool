package sbp.school.kafka.configuration;

import java.util.Properties;

public class KafkaConfig {

    private final Properties properties;

    public KafkaConfig() {
        this.properties = new Properties();
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "sbp.school.kafka.serializer.OperationDeserializer");
        properties.put("group.id", "kafka-consumer");
        properties.put("enable.auto.commit", "false");

    }

    public Properties getProperties() {
        return properties;
    }
}
