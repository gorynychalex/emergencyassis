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

    public SocialServiceCatalog() {    }

    public SocialServiceCatalog(String title, List<SocialService> socialServices) {
        this.title = title;
        this.socialServices = socialServices;
    }

    public SocialServiceCatalog(String id, String title, List<SocialService> socialServices) {
        this(title,socialServices);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<SocialService> getSocialServices() {
        return socialServices;
    }

    public void setSocialServices(List<SocialService> socialServices) {
        this.socialServices = socialServices;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Date> getDateEnable() {
        return dateEnable;
    }

    public void setDateEnable(List<Date> dateEnable) {
        this.dateEnable = dateEnable;
    }
}
