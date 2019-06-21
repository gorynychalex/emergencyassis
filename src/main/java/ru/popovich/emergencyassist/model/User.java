package ru.popovich.emergencyassist.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.popovich.emergencyassist.model.personal.UserPersonal;

import java.util.Date;
import java.util.List;

public class User {

    private String id;
    private String nickname;
    private String password;
    private boolean enable = true;
    private Date dateCreation;

    private UserPersonal personal;

    private UserRole role;
    private Organization organization;

    private String descriptions;
    private String notes;

    private List<UserRelation> userRelations;

    public User() {
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(String id, String nickname, String password) {
        this(nickname,password);
        this.id = id;
    }

    public User(String id, String nickname, String password, UserRole userRole) {
        this(id, nickname,password);
        this.role = userRole;
    }

    public User(String id, String nickname, String password, UserRole userRole, List<UserRelation> userRelations) {
        this(id, nickname,password,userRole);
        this.userRelations = userRelations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(UserPersonal personal) {
        this.personal = personal;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(List<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }
}
