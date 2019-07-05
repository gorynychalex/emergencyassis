package ru.popovich.emergencyassist.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    private String typeOf;

    @ElementCollection(targetClass = String.class)
    private List<String> address;

    @ElementCollection(targetClass = String.class)
    private List<String> phone;

    @ElementCollection(targetClass = String.class)
    private List<String> otherContacts;

    @ElementCollection(targetClass = String.class)
    private List<String> coordinates;

    @ElementCollection(targetClass = String.class)
    private List<String> photoUrls;

    @ElementCollection(targetClass = String.class)
    private List<String> openHours;

    @ManyToMany
    @ElementCollection(targetClass = User.class)
    private List<User> users;

    private String description;

    private String notes;

    private Date dateCreation;

    public Organization() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
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

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<String> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<String> openHours) {
        this.openHours = openHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
