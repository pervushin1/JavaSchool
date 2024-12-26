package sbp.school.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class OperationSerializer implements Serializer {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Override
    public byte[] serialize(String s, Object operation) {
        if (operation != null) {
            try {
                return objectMapper.writeValueAsString(operation).getBytes(StandardCharsets.UTF_8);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
