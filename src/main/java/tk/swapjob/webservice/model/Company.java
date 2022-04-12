package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    private Integer id;
    private String name;
    private String coordinates;
    private String email;
    private String imageUrl;
    private String description;
    private Boolean isVisible;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="Offer",
            joinColumns = @JoinColumn(name = "companyId"))
    private Set<Offer> offerList = new HashSet<>();

    //region Constructors
    //Every time constructor
    public Company(Integer id, String name, String coordinates, String email, String imageUrl, String description, Boolean isVisible) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.email = email;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isVisible = isVisible;
    }

    //First time constructor
    public Company(Integer id, String name, String coordinates, String email, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.email = email;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isVisible = true;
    }

    public Company() {
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
    //endregion
}
