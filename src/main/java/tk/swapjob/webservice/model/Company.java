package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String coordinates;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 100)
    private String imageUrl;
    @Column(length = 300, nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean isVisible;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Set<Offer> offerList = new HashSet<>();

    //region Constructors
    //Every time constructor
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

    public Set<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(Set<Offer> offerList) {
        this.offerList = offerList;
    }

    //endregion
}
