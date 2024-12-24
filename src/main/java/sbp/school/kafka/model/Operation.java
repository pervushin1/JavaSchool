package sbp.school.kafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Operation {

    private String type;
    private String value;
    private String account;
    private LocalDate date;
}
