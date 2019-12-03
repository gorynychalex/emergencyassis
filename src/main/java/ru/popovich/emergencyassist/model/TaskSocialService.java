package ru.popovich.emergencyassist.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
public class TaskSocialService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private SocialService socialService;

    @ManyToOne
    private User needy;

    @ManyToOne
    private User employee;

    private Date dateCreate;

    private Date dateStart;

    private Date dateStop;

    private Duration duration;

    private boolean enable = true;

    private Status status;

    @Range(min=1,max=10)
    private Integer priority;

    @JsonIgnore
    @Version
    private Long version;

    public TaskSocialService() {}

    public TaskSocialService(SocialService socialService, User needy) {
        this.socialService = socialService;
        this.needy = needy;
        this.dateCreate = new Date();
        this.status = Status.NEW;
        this.priority = 5;
    }

    public TaskSocialService(Long id, SocialService socialService, User needy){
        this(socialService, needy);
        this.id = id;
    }

    public TaskSocialService(SocialService socialService, User needy, User employee) {
        this(socialService, needy);
        this.employee = employee;
    }

    public TaskSocialService(Long id, SocialService socialService, User needy, User employee){
        this(id, socialService, needy);
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialService getSocialService() {
        return socialService;
    }

    public void setSocialService(SocialService socialService) {
        this.socialService = socialService;
    }

    public User getNeedy() {
        return needy;
    }

    public void setNeedy(User needy) {
        this.needy = needy;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStop() {
        return dateStop;
    }

    public void setDateStop(Date dateStop) {
        this.dateStop = dateStop;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
