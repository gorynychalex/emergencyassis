package ru.popovich.emergencyassist.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.popovich.emergencyassist.model.personal.UserPersonal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity(name = "usr")
public class User {

    @Id
    private String nickname;

    private String password;

    private boolean enable = true;

    private String email;

    private Locale locale;

    private Date dateCreation;

    private String sub;

    @ElementCollection(targetClass = Date.class)
    private List<Date> dateEnable;

    @OneToOne
    private UserPersonal personal;

    private UserRole role;

    @ManyToOne
    private Organization organization;

    private String descriptions;
    private String notes;

//    private List<UserRelation> userRelations;

    public User() {
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(String nickname, String password, UserRole userRole) {
        this(nickname,password);
        this.role = userRole;
    }

    @Override
    public String toString() {
        return this.nickname + " " + this.enable;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

//    public List<UserRelation> getUserRelations() {
//        return userRelations;
//    }
//
//    public void setUserRelations(List<UserRelation> userRelations) {
//        this.userRelations = userRelations;
//    }


    public List<Date> getDateEnable() {
        return dateEnable;
    }

    public void setDateEnable(List<Date> dateEnable) {
        this.dateEnable = dateEnable;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(UserPersonal personal) {
        this.personal = personal;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
