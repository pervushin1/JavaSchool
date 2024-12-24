package sbp.school.kafka;

import sbp.school.kafka.service.ConsumerService;

import java.util.List;

public class Main {

    private static final ConsumerService consumerService = new ConsumerService();

    public static void main(String[] args) {

        consumerService.listen(List.of("test-topic"));
    }
}
