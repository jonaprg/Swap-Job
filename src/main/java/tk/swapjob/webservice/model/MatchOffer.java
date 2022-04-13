package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "match_offer")
public class MatchOffer implements Serializable {
    @Id
    private Integer id;
    private Boolean isFinalized;
    private Boolean isContracted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    //region Constructors
    public MatchOffer() {
    }

    //endregion

    //region Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFinalized() {
        return isFinalized;
    }

    public void setFinalized(Boolean finalized) {
        isFinalized = finalized;
    }

    public Boolean getContracted() {
        return isContracted;
    }

    public void setContracted(Boolean contracted) {
        isContracted = contracted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    //endregion
}
