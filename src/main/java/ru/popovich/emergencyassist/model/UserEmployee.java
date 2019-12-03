package ru.popovich.emergencyassist.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity(name = "usr_employee")
public class UserEmployee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Version
    private Long version;

    private UserEmployeeRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UserEmployeeRole getRole() {
        return role;
    }

    public void setRole(UserEmployeeRole role) {
        this.role = role;
    }
}
