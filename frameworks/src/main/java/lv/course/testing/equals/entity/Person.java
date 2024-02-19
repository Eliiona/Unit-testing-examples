package lv.course.testing.equals.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lv.course.testing.equals.annotations.Connection;
import lv.course.testing.equals.annotations.Id;
import lv.course.testing.equals.annotations.Version;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString()
public class Person implements CloneableEntity<Person> {

    @Id
    private long id;
    @Connection
    private List<Account> accounts;
    private String name;
    private String surname;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    @Version
    int version;
    boolean draft;



}
