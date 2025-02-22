package sbp.school.kafka.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Transaction {

    private String id;
    private String operationId;
    private LocalDateTime time;
}
