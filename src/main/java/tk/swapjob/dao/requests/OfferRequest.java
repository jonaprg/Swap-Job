package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class OfferRequest {
    private String title;
    private String description;
    private Float salary;
    private Boolean isRemote;
    private Boolean isVisible;
    private Integer labour;
    private List<Integer> skillList;

    public OfferRequest() {
    }

    public OfferRequest(String title, String description, Float salary, Boolean isRemote, Boolean isVisible, Integer labour, List<Integer> skillList) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.isRemote = isRemote;
        this.isVisible = isVisible;
        this.labour = labour;
        this.skillList = skillList;
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

    public Integer getLabour() {
        return labour;
    }

    public void setLabour(Integer labour) {
        this.labour = labour;
    }

    public List<Integer> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Integer> skillList) {
        this.skillList = skillList;
    }

    @JsonIgnore
    public boolean isValid() {
        return this.title != null && this.description != null
                && this.salary != null && this.isRemote != null
                && this.isVisible != null && this.labour != null
                && this.skillList != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
