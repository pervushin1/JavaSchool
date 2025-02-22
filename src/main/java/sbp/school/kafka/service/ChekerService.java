package sbp.school.kafka.service;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;


@Getter
public class ChekerService {


    public void showTheMap(ConsumerService consumerService, ProducerService producerService) {

        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println("ConsumerMap: " + consumerService.consumerMap);
                System.out.println("ProducerMap: " + producerService.producerMap);
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void retrySend(ConsumerService consumerService, ProducerService producerService) {
        System.out.println("Start retry");
        if (!isHashEquals(consumerService.consumerMap, producerService.producerMap)) {
            producerService.getTransactions().stream()
                    .filter(transaction -> transaction.getTime().isBefore(LocalDateTime.now().minus(Duration.ofMinutes(5))))
                    .map(transaction -> producerService.send(producerService.getOperationByTransaction(transaction)))
                    .toList();
        }
    }


    private boolean isHashEquals(Map<String, String> consumerMap, Map<Integer, String> producerMap) {
        int consumerHashSum = 0;
        int producerHashSum = 0;
        for (Map.Entry<String, String> entry : consumerMap.entrySet()) {
            consumerHashSum += entry.getValue().hashCode();
        }
        for (Map.Entry<Integer, String> entry : producerMap.entrySet()) {
            producerHashSum += entry.getValue().hashCode();
        }
        System.out.println("Consumer: " + consumerHashSum + ", Producer: " + producerHashSum);
        return consumerHashSum == producerHashSum;

    }

}
