package ru.popovich.emergencyassist.model.personal;

import java.util.Date;

public class Document {

    private String id;

    private String title;
    private String type;
    private String codeIssueState;
    private String number;
    private String serial;

    private String authority;
    private Date dateIssue;
    private Date dateValidity;
    private Date dateExpiry;

    private String natinality;
    private UserSex userSex;
    private String placeBirth;
    private String country;
    private String city;
    private Date dateBirth;

    private String holderSign;
    private String description;

    // Include date
    private Date dateCreation;
}
