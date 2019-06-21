package ru.popovich.emergencyassist.model;


import ru.popovich.emergencyassist.model.personal.UserPersonal;

import java.util.Date;

public class User {

    private String id;
    private String nickname;
    private String password;
    private boolean enable;
    private Date dateCreation;

    private UserPersonal personal;

    private UserRole role;
    private Organization organization;

    private String descriptions;
    private String notes;
}
