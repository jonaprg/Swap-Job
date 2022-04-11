package tk.swapjob.webservice.model;

import java.util.List;

public class Company {
    private Integer id;
    private String name;
    private String coordinates;
    private String email;
    private String imageUrl;
    private String description;
    private Boolean isVisible;

    private List<Offer> offerList;

    //region Constructors
    //Every time constructor
    public Company(Integer id, String name, String coordinates, String email, String imageUrl, String description, Boolean isVisible, List<Offer> offerList) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.email = email;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isVisible = isVisible;
        this.offerList = offerList;
    }

    //First time constructor
    public Company(Integer id, String name, String coordinates, String email, String imageUrl, String description, List<Offer> offerList) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.email = email;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isVisible = true;
        this.offerList = offerList;
    }
    //endregion

    //region Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
    //endregion
}
