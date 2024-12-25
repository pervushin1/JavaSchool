package sbp.school.kafka.config;

import java.util.Properties;

public class KafkaConfig {

    public static Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "sbp.school.kafka.serializer.OperationSerializer");
        return properties;
    }
}
