package ru.popovich.emergencyassist.model.personal;

import java.util.Date;
import java.util.List;

public class UserPersonal {

    private String id;

    private boolean active;

    private String firstname;
    private String middlename;
    private String lastname;

    private UserSex userSex;

    private List<String> address;
    private List<String> phone;
    private List<String> otherContacts;

    private List<Document> documents;

    private Date dateOfBirth;
    private String placeBirth;
    private String placeResidence;

    private List<Employment> employment;
    private List<SocialFactor> SocialFactors;

}
