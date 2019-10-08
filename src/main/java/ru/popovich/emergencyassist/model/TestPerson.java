package ru.popovich.emergencyassist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TestPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="person_persons", joinColumns = @JoinColumn(name="personId"), inverseJoinColumns = @JoinColumn(name = "relationId"))
    private List<TestPerson> testPersonList1;

    @ManyToMany
    @JoinTable(name="person_persons", inverseJoinColumns = @JoinColumn(name="personId"), joinColumns = @JoinColumn(name = "relationId"))
    private List<TestPerson> testPersonList2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestPerson> getTestPersonList1() {
        return testPersonList1;
    }

    public void setTestPersonList1(List<TestPerson> testPersonList1) {
        this.testPersonList1 = testPersonList1;
    }

    public List<TestPerson> getTestPersonList2() {
        return testPersonList2;
    }

    public void setTestPersonList2(List<TestPerson> testPersonList2) {
        this.testPersonList2 = testPersonList2;
    }
}
