package ru.popovich.emergencyassist.model;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class SocialService {

    private String id;

    private String title;

    private String description;

    private float cost;

//    private Duration duration; //SLA duration, plan

    private boolean oneTime;

    private int periods;

    private boolean enable = true;

//    private Date dateCreation;

//    private List<Date> dateEnable;

    public SocialService(){}

    public SocialService(String title, float cost) {
        this.title = title;
        this.cost = cost;
    }

    public SocialService(String id, String title, float cost){
        this(title, cost);
        this.id = id;
    }
}
