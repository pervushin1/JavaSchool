package sbp.school.kafka.service;

import lombok.Getter;
import sbp.school.kafka.repository.OperationRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ChekerService {

    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    ProducerService producerService = new ProducerService();


    public void showTheMap() {

        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(map);
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void retrySend(OperationRepository operationRepository) {
        System.out.println("Checker starts work");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("null".equals(entry.getValue())) {
                producerService.sendConfirmation(operationRepository.get(entry.getKey()), true);
                System.out.println("Resend complete " + entry.getKey());
            }
        }
    }

}
