package tk.swapjob.webservice.model;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer postalCode;
    private String phone;
    private Timestamp birthDate;
    private Boolean isVisible;
    private String description;
    private Status status;

    private List<Skill> skillList;
    private List<Preference> preferenceList;
    private List<MatchOffer> offerMatchList;

    //region Constructors
    //Every time constructor
    public User(Integer id, String firstName, String lastName, String email, Integer postalCode, String phone, Timestamp birthDate, Boolean isVisible, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postalCode = postalCode;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isVisible = isVisible;
        this.description = description;
    }

    //First time constructor when creating new User
    public User(Integer id, String firstName, String lastName, String email, Integer postalCode, String phone, Timestamp birthDate, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postalCode = postalCode;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isVisible = true;
        this.description = description;
    }
    //endregion

    //region Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<Preference> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<Preference> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public List<MatchOffer> getOfferMatchList() {
        return offerMatchList;
    }

    public void setOfferMatchList(List<MatchOffer> offerMatchList) {
        this.offerMatchList = offerMatchList;
    }
    //endregion
}
