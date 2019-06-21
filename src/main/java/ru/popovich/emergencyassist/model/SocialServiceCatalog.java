package ru.popovich.emergencyassist.model;

import java.util.Date;
import java.util.List;

public class SocialServiceCatalog {

    private String id;

    private String title;

    private String description;

    private List<SocialService> socialServices;

    private Organization organization;

    private boolean free;

    private boolean enable = true;

    private Date dateCreation;

    private List<Date> dateEnable;

    public SocialServiceCatalog(String title, List<SocialService> socialServices) {
        this.title = title;
        this.socialServices = socialServices;
    }

    public SocialServiceCatalog(String id, String title, List<SocialService> socialServices) {
        this(title,socialServices);
        this.id = id;
    }
}
