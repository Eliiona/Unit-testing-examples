package lv.course.testing.equals.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lv.course.testing.equals.annotations.Connection;
import lv.course.testing.equals.annotations.Id;

import java.util.List;

@Getter
@Setter
@ToString(of = {"accountGroup", "growthFactor", "interestRate", "liabilityType"})
public class Account implements CloneableEntity<Account> {

    @Id
    private long id;
    @Connection
    private Person accountHolder;
    @Connection
    private List<Payment> payments;
    private String accountGroup;
    private String accountNumber;
    private boolean credit;
    private long balance;
    private double interestRate;
    private String growthFactor;
    private String liabilityType;
}
