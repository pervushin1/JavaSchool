package sbp.school.kafka;

import sbp.school.kafka.model.Operation;
import sbp.school.kafka.service.ProducerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    private static final ProducerService producerService = new ProducerService();

    public static void main(String[] args) {


        producerService.send(Operation.builder()
                .account("40754122585482")
                .date(LocalDate.of(2024, Month.JANUARY, 1))
                .type("openAccount")
                .value("0")
                .build());

        producerService.send(Operation.builder()
                .account("6545423156574")
                .date(LocalDate.of(2024, Month.JANUARY, 1))
                .type("depositAccount")
                .value("500000")
                .build());

    }
}
