package tk.swapjob.webservice.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private Integer postalCode;
    private String phone;
    private Timestamp birthDate;
    private Boolean isVisible;
    private String description;
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserSkills",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "skillId"))
    private Set<Skill> skillList = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserPreferences",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "preferenceId"))
    private Set<Preference> preferenceList = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserMatchOffer",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "offerId"))
    private Set<MatchOffer> offerList = new HashSet<>();

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

    public User() {

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

    //endregion
}
