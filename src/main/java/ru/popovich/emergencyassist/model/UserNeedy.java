package ru.popovich.emergencyassist.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity(name = "usr_needy")
public class UserNeedy extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Version
    private Long version;


    private UserNeedyRole role;

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

    public UserNeedyRole getRole() {
        return role;
    }

    public void setRole(UserNeedyRole role) {
        this.role = role;
    }
}
