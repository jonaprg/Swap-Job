package tk.swapjob.webservice.model;

public class MatchOffer {
    private Integer userId;
    private Integer offerId;
    private Boolean isFinalized;
    private Boolean isContracted;

    private User user;
    private Offer offer;

    //region Constructors
    public MatchOffer(Integer userId, Integer offerId, Boolean isFinalized, Boolean isContracted, User user, Offer offer) {
        this.userId = userId;
        this.offerId = offerId;
        this.isFinalized = isFinalized;
        this.isContracted = isContracted;
        this.user = user;
        this.offer = offer;
    }

    public MatchOffer(Integer userId, Integer offerId, Boolean isFinalized, Boolean isContracted) {
        this.userId = userId;
        this.offerId = offerId;
        this.isFinalized = isFinalized;
        this.isContracted = isContracted;
        //TODO: yet to populate user and offer
    }
    //endregion

    //region Getters & Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
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
