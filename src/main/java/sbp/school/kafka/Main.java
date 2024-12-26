package sbp.school.kafka;

import sbp.school.kafka.model.Operation;
import sbp.school.kafka.repository.OperationRepository;
import sbp.school.kafka.service.ChekerService;
import sbp.school.kafka.service.ConsumerService;
import sbp.school.kafka.service.ProducerService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static sbp.school.kafka.deserializer.DeserializerType.STRING;

public class Main {

    private static final ProducerService producerService = new ProducerService();

    private static final ConsumerService consumerService = new ConsumerService();

    private static final ChekerService chekerService = new ChekerService();

    private static final OperationRepository operationRepository = new OperationRepository();

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        executorService.submit(() -> consumerService.listen(List.of("confirmation_topic"), STRING.getValue(), chekerService));

        Operation operation1 = Operation.builder()
                .id(UUID.randomUUID().toString())
                .account("40754122585482")
                .date(LocalDate.of(2024, Month.JANUARY, 1))
                .type("openAccount")
                .value("0")
                .build();

        Operation operation2 = Operation.builder()
                .id(UUID.randomUUID().toString())
                .account("6545423156574")
                .date(LocalDate.of(2024, Month.JANUARY, 1))
                .type("depositAccount")
                .value("500000")
                .build();

        operationRepository.save(operation1);
        operationRepository.save(operation2);

        boolean send1 = producerService.send(operation1);

        String producerID1 = producerService.sendConfirmation(operation1, send1);

        chekerService.getMap().put(producerID1, "null");

        boolean send2 = producerService.send(operation2);

        String producerID2 = producerService.sendConfirmation(operation2, send2);

        chekerService.getMap().put(producerID2, "null");


    }
}
