package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MatchOfferRequest {
    private Long offerId;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    @JsonIgnore
    public boolean isValid() {
        return offerId != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
