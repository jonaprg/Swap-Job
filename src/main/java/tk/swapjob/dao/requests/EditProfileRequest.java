package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tk.swapjob.model.Preference;
import tk.swapjob.model.Skill;

import java.util.List;

public class EditProfileRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer postalCode;
    private String phone;
    private String birthDate;
    private Boolean isVisible;
    private String description;
    private Integer status_id;
    private List<Skill> skillList;
    private List<Preference> preferenceList;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
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

    @JsonIgnore
    public boolean isValid() {
        return email != null && firstName != null
                && lastName != null && postalCode != null && phone != null
                && birthDate != null && description != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
