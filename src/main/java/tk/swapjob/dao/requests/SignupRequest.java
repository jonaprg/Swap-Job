package tk.swapjob.dao.requests;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.sql.Timestamp;

public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(min = 6, max = 50)
    private String firstName;
    @NotBlank
    @Size(min = 6, max = 50)
    private String lastName;
    @NotBlank
    private Integer postalCode;
    @NotBlank
    @Size(min = 6, max = 50)
    private String phone;
    @NotBlank
    @Size(min = 6, max = 50)
    private String birthDate;
    @NotBlank
    @Size(min = 6, max = 300)
    private String description;

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
}