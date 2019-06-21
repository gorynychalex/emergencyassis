package ru.popovich.emergencyassist.model;

import java.util.Date;
import java.util.List;

public class Organization {

    private String id;

    private String name;

    private String typeOf;

    private List<String> address;

    private List<String> phone;

    private List<String> otherContacts;

    private List<String> coordinates;

    private List<String> photoUrls;

    private List<String> openHours;

    private String description;

    private String notes;

    private Date dateCreation;
}
