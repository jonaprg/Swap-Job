package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContractedRequest {

    private Long matchOfferId;
    private Boolean isContracted;

    public ContractedRequest() {
    }

    public ContractedRequest(Long matchOfferId, Boolean isContracted) {
        this.matchOfferId = matchOfferId;
        this.isContracted = isContracted;
    }

    public Long getMatchOfferId() {
        return matchOfferId;
    }

    public void setMatchOfferId(Long matchOfferId) {
        this.matchOfferId = matchOfferId;
    }

    public Boolean getContracted() {
        return isContracted;
    }

    public void setContracted(Boolean contracted) {
        isContracted = contracted;
    }

    @JsonIgnore
    public boolean isValid() {
        return matchOfferId != null && isContracted != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
