package ru.popovich.emergencyassist.model;


import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import ru.popovich.emergencyassist.model.personal.UserPersonal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Entity(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname;

    private String password;

    private String firstname;
    private String middlename;
    private String lastname;

    private boolean enable = true;

    private String address;

    private String phone;

    private String mobile;

    private String email;

    private Locale locale;

    private LocalDateTime dateCreation;

    private String sub;

    @Version
    private Long version;

    @Transient
    @ElementCollection(targetClass = LocalDateTime.class, fetch = FetchType.LAZY)
    private List<LocalDateTime> dateEnable;

    @OneToOne
    private UserPersonal personal;

    private UserRole role;

    @Transient
    @ElementCollection(targetClass = UserRole.class,fetch = FetchType.LAZY)
    private List<UserRole> roles;

    @ManyToOne
    private Organization organization;


    @Transient
    @ManyToMany
    @ElementCollection(targetClass = Organization.class, fetch = FetchType.LAZY)
    private List<Organization> organizations;

    @Transient
    @ElementCollection(targetClass = LocalDateTime.class, fetch = FetchType.LAZY)
    private List<LocalDateTime> authDateTime;

    private String descriptions;
    private String notes;

//    private List<UserRelation> userRelations;

    @Transient
    @ElementCollection(targetClass = User.class)
    private List<User> users;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
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

    public void setDateEnable(List<LocalDateTime> dateEnable) {
        this.dateEnable = dateEnable;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<LocalDateTime> getDateEnable() {
        return dateEnable;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<LocalDateTime> getAuthDateTime() {
        return authDateTime;
    }

    public void setAuthDateTime(List<LocalDateTime> authDateTime) {
        this.authDateTime = authDateTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
