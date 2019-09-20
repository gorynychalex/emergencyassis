package ru.popovich.emergencyassist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@JsonPropertyOrder({"id","title","socialService"})
@ToString
public class SocialServiceCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 1024)
    private String title;

    private String description;


    @OneToMany(mappedBy = "socialServiceCatalog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocialService> socialService;

    @ManyToOne
    private Organization organization;

    private boolean free;

    private boolean enable = true;

    private LocalDateTime dateCreation;

    private LocalDateTime dateEnable;

    public SocialServiceCatalog() {    }

    public SocialServiceCatalog(String title, List<SocialService> socialService) {
        this.title = title;
        this.socialService = socialService;
    }

    public SocialServiceCatalog(Long id, String title, List<SocialService> socialServices) {
        this(title,socialServices);
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

    public List<SocialService> getSocialService() {
        return socialService;
    }

    public void setSocialService(List<SocialService> socialService) {
        this.socialService = socialService;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateEnable() {
        return dateEnable;
    }

    public void setDateEnable(LocalDateTime dateEnable) {
        this.dateEnable = dateEnable;
    }


}
