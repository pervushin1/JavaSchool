package sbp.school.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Operation {

    private String type;
    private String value;
    private String account;
    private LocalDate date;
}
