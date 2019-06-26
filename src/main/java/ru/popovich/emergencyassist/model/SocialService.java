package ru.popovich.emergencyassist.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.List;


@Entity
public class SocialService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private float cost;

//    private Duration duration; //SLA duration, plan

    private boolean oneTime;

    private int periods;

    private boolean enable = true;

//    @ElementCollection(targetClass = String.class)
//    private Date dateCreation;

//    @ElementCollection(targetClass = Date.class)
//    private List<Date> dateEnable;

    public SocialService() {}

    public SocialService(String title, float cost) {
        this.title = title;
        this.cost = cost;
//        this.dateCreation = new Date();
    }

    public SocialService(String title, float cost, int periods){
        this(title, cost);
        this.periods = periods;
    }

    public SocialService(Long id, String title, float cost){
        this(title, cost);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

//    public Duration getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Duration duration) {
//        this.duration = duration;
//    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

//    public Date getDateCreation() {
//        return dateCreation;
//    }
//
//    public void setDateCreation(Date dateCreation) {
//        this.dateCreation = dateCreation;
//    }

//    public List<Date> getDateEnable() {
//        return dateEnable;
//    }
//
//    public void setDateEnable(List<Date> dateEnable) {
//        this.dateEnable = dateEnable;
//    }
}
