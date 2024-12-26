package sbp.school.kafka.config;

import java.util.Properties;

public class ProducerKafkaConfig {

    private final Properties properties = new Properties();

    public ProducerKafkaConfig(String valueSerializer) {
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", valueSerializer);
    }


    public Properties getProperties() {
        return properties;
    }
}
