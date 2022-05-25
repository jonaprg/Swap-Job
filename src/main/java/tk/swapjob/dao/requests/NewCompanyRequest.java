package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NewCompanyRequest {

    private String name;
    private String coordinates;
    private String email;
    private String imageUrl;
    private String description;
    private Boolean isVisible;
    private String password;
    private Integer postalCode;

    public NewCompanyRequest() {
    }

    public NewCompanyRequest(String name, String coordinates, String email, String imageUrl, String description, Boolean isVisible, String password, Integer postalCode) {
        this.name = name;
        this.coordinates = coordinates;
        this.email = email;
        this.imageUrl =imageUrl;
        this.description = description;
        this.isVisible = isVisible;
        this.password = password;
        this.postalCode = postalCode;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    @JsonIgnore
    public boolean isValid() {
        return name != null && coordinates != null && email != null
                && imageUrl != null && description != null && isVisible != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
