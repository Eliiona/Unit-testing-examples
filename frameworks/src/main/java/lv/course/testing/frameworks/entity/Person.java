package lv.course.testing.frameworks.entity;

import lombok.Getter;
import lombok.Setter;
import lv.course.testing.frameworks.annotations.Connection;
import lv.course.testing.frameworks.annotations.Id;
import lv.course.testing.frameworks.annotations.Version;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Person implements CloneableEntity<Person> {

    @Id
    private long id;
    @Connection
    private List<Account> accounts = new ArrayList<>();
    private String name;
    private String surname;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    @Version
    int version;
    boolean draft;


}
