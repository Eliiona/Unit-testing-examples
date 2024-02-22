package lv.course.testing.frameworks.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Payment {

    private long id;
    private long amount;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;

}
