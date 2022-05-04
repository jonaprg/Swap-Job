package tk.swapjob.dao.requests;

public class MatchOfferRequest {
    private Long offerId;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public boolean isValid() {
        return offerId != null;
    }

    public boolean isInvalid() {
        return !isValid();
    }
}
