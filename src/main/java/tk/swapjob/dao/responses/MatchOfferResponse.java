package tk.swapjob.dao.responses;

import tk.swapjob.model.MatchOffer;
import tk.swapjob.model.User;

import java.io.Serializable;

public class MatchOfferResponse implements Serializable {
    private Integer id;
    private Boolean isFinalized;
    private Boolean isContracted;
    private User user;
    private OfferResponse offer;

    public MatchOfferResponse() {
    }

    public MatchOfferResponse(MatchOffer mathOffer) {
        this.id = mathOffer.getId();
        this.isFinalized = mathOffer.getFinalized();
        this.isContracted = mathOffer.getContracted();
        this.user = mathOffer.getUser();
        this.offer = new OfferResponse(mathOffer.getOffer());
    }

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

    public OfferResponse getOffer() {
        return offer;
    }

    public void setOffer(OfferResponse offer) {
        this.offer = offer;
    }
}
