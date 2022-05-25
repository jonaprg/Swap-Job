package tk.swapjob.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Email
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 300, nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private Integer postalCode;
    @Column(length = 50, nullable = false)
    private String phone;
    @Column(nullable = false)
    private Timestamp birthDate;
    @Column(nullable = false)
    private Boolean isVisible;
    @Column(length = 300, nullable = false)
    private String description;
    @Column(nullable = false)
    @JsonIgnore
    private boolean isCompanyUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_skills",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "is_skill"))
    private Set<Skill> skillList = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_preferences",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_preference"))
    private Set<Preference> preferenceList = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Set<MatchOffer> matchOfferList = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company")
    @JsonIgnore
    private Company company;

    //region Constructors
    //Every time constructor
    public User() {
    }

    public User(String email, String password, String firstName, String lastName, Integer postalCode, String phone, Timestamp birthDate, String description, boolean isCompanyUser, Status status) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isVisible = true;
        this.description = description;
        this.isCompanyUser = isCompanyUser;
        this.status = status;
    }
    //endregion

    //region Getters & Setters
    @JsonIgnore
    public boolean isCompanyUser() {
        return isCompanyUser;
    }

    public void setCompanyUser(boolean companyUser) {
        isCompanyUser = companyUser;
    }

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

    public Set<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<Skill> skillList) {
        this.skillList = skillList;
    }

    public Set<Preference> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(Set<Preference> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public Set<MatchOffer> getMatchOfferList() {
        return matchOfferList;
    }

    public void setMatchOfferList(Set<MatchOffer> matchOfferList) {
        this.matchOfferList = matchOfferList;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addSkill(Skill skill) {
        this.skillList.add(skill);
    }

    public void addPreference(Preference preference) {
        this.preferenceList.add(preference);
    }

    //endregion

    @JsonIgnore
    public User getProfile() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPostalCode(this.postalCode);
        user.setPhone(this.phone);
        user.setBirthDate(this.birthDate);
        user.setVisible(this.isVisible);
        user.setDescription(this.description);
        user.setStatus(this.status);
        user.setSkillList(this.skillList);
        return user;
    }

    //endregion
}
