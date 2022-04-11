package tk.swapjob.webservice.model;

import java.util.List;

public class Offer {
    private Integer id;
    private String title;
    private String description;
    private Float salary;
    private Boolean isRemote;
    private Boolean isVisible;

    private List<Skill> skillList;
    private List<MatchOffer> matchOfferList;
    private List<Preference> preferenceList;

    //region Constructors
    public Offer(Integer id, String title, String description, Float salary, Boolean isRemote, Boolean isVisible, List<Skill> skillList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.isRemote = isRemote;
        this.isVisible = isVisible;
        this.skillList = skillList;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<MatchOffer> getMatchOfferList() {
        return matchOfferList;
    }

    public void setMatchOfferList(List<MatchOffer> matchOfferList) {
        this.matchOfferList = matchOfferList;
    }

    public List<Preference> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<Preference> preferenceList) {
        this.preferenceList = preferenceList;
    }

    //endregion
}
