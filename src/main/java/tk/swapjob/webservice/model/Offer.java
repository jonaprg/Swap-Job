package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Float salary;
    private Boolean isRemote;
    private Boolean isVisible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "offer_skills",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skillList = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private Set<MatchOffer> matchOfferList = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "offer_preferences",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "preference_id"))
    private Set<Preference> preferenceList = new HashSet<>();

    //region Constructors
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<Skill> skillList) {
        this.skillList = skillList;
    }

    public Set<MatchOffer> getMatchOfferList() {
        return matchOfferList;
    }

    public void setMatchOfferList(Set<MatchOffer> matchOfferList) {
        this.matchOfferList = matchOfferList;
    }

    public Set<Preference> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(Set<Preference> preferenceList) {
        this.preferenceList = preferenceList;
    }

    //endregion
}
