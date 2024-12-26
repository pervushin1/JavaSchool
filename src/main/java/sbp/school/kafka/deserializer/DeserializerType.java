package sbp.school.kafka.deserializer;

public enum DeserializerType {

    STRING("org.apache.kafka.common.serialization.StringDeserializer"),
    OPERATION("sbp.school.kafka.deserializer.OperationDeserializer");

    private String value;

    DeserializerType(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
