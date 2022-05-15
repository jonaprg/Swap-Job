package tk.swapjob.dao.responses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import tk.swapjob.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OfferResponse implements Serializable {

    private Long id;

    private String title;

    private String description;

    private Float salary;

    private Boolean isRemote;

    private Integer labour;

    private String companyName;

    private String companyImage;

    private String coordinates;

    private Set<Skill> skillList = new HashSet<>();

    private Set<Preference> preferenceList = new HashSet<>();

    //region Constructors
    public OfferResponse() {
    }

    public OfferResponse(Offer offer) {
        this.title = offer.getTitle();
        this.description = offer.getDescription();
        this.isRemote = offer.getRemote();
        this.salary = offer.getSalary();
        this.labour = offer.getLabour();
        this.id = offer.getId();
        this.coordinates = offer.getCompany().getCoordinates();
        this.companyName = offer.getCompany().getName();
        this.companyImage = offer.getCompany().getImageUrl();
        this.skillList = offer.getSkillList();
        this.preferenceList = offer.getPreferenceList();
    }

    //endregion

    //region Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getLabour() {
        return labour;
    }

    public void setLabour(Integer labour) {
        this.labour = labour;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    //endregion
}
