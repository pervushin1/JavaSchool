package sbp.school.kafka.serializer;

public enum SerializerType {

    STRING("org.apache.kafka.common.serialization.StringSerializer"),
    OPERATION("sbp.school.kafka.serializer.OperationSerializer");

    private String value;

    SerializerType(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
