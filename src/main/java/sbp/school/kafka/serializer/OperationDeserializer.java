package sbp.school.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;
import sbp.school.kafka.model.Operation;

import java.io.IOException;
import java.util.Optional;

public class OperationDeserializer implements Deserializer {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();


    @Override
    public Object deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return Optional.empty();
        } else {
            try {
                return objectMapper.readValue(data, Operation.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
