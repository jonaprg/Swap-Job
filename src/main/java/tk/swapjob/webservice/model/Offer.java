package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Float salary;
    private Boolean isRemote;
    private Boolean isVisible;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OfferSkills",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "skillId"))
    private Set<Skill> skillList = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserMatchOffer",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<MatchOffer> offerList = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OfferPreferences",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "preferenceId"))
    private Set<Preference> preferenceList = new HashSet<>();

    //region Constructors
    public Offer(Integer id, String title, String description, Float salary, Boolean isRemote, Boolean isVisible) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.isRemote = isRemote;
        this.isVisible = isVisible;
    }

    public Offer() {
    }

    //endregion

    //region Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
    //endregion
}
