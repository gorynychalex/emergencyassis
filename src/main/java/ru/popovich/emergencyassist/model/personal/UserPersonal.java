package ru.popovich.emergencyassist.model.personal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean active;

    private String firstname;
    private String middlename;
    private String lastname;

    private UserSex userSex;

    @ElementCollection(targetClass = String.class)
    private List<String> address;

    @ElementCollection(targetClass = String.class)
    private List<String> phone;

    @ElementCollection(targetClass = String.class)
    private List<String> otherContacts;

    @OneToMany
    @ElementCollection(targetClass = Document.class)
    private List<Document> documents;

    private Date dateOfBirth;
    private String placeBirth;
    private String placeResidence;

    @ElementCollection(targetClass = Employment.class)
    private List<Employment> employment;

    @ElementCollection(targetClass = SocialFactor.class)
    @Enumerated(EnumType.STRING)
    private List<SocialFactor> socialFactors;

    public UserPersonal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getOtherContacts() {
        return otherContacts;
    }

    public void setOtherContacts(List<String> otherContacts) {
        this.otherContacts = otherContacts;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getPlaceResidence() {
        return placeResidence;
    }

    public void setPlaceResidence(String placeResidence) {
        this.placeResidence = placeResidence;
    }

    public List<Employment> getEmployment() {
        return employment;
    }

    public void setEmployment(List<Employment> employment) {
        this.employment = employment;
    }

    public List<SocialFactor> getSocialFactors() {
        return socialFactors;
    }

    public void setSocialFactors(List<SocialFactor> socialFactors) {
        this.socialFactors = socialFactors;
    }
}
