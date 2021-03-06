package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tk.swapjob.model.Preference;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class SignupRequest {
    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 50)
    private String firstName;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 50)
    private String lastName;
    @NotBlank
    @NotNull
    private Integer postalCode;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 50)
    private String phone;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 50)
    private String birthDate;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 300)
    private String description;
    @NotBlank
    @NotNull
    private boolean isCompanyUser;

    private List<Integer> skillIdList;

    private List<Preference> preferenceIdList;

    public SignupRequest() {
        skillIdList = new ArrayList<>();
        preferenceIdList = new ArrayList<>();
    }

    public List<Integer> getSkillIdList() {
        return skillIdList;
    }

    public void setSkillIdList(List<Integer> skillIdList) {
        this.skillIdList = skillIdList;
    }

    public List<Preference> getPreferenceIdList() {
        return preferenceIdList;
    }

    public void setPreferenceIdList(List<Preference> preferenceIdList) {
        this.preferenceIdList = preferenceIdList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompanyUser() {
        return isCompanyUser;
    }

    public void setCompanyUser(boolean companyUser) {
        isCompanyUser = companyUser;
    }

    @JsonIgnore
    public boolean isValid() {
        return email != null && password != null && firstName != null
                && lastName != null && postalCode != null && phone != null
                && birthDate != null && description != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}