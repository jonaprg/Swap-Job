package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContractedRequest {

    private Integer matchOfferId;
    private Boolean isContracted;

    public ContractedRequest() {
    }

    public ContractedRequest(Integer matchOfferId, Boolean isContracted) {
        this.matchOfferId = matchOfferId;
        this.isContracted = isContracted;
    }

    public Integer getMatchOfferId() {
        return matchOfferId;
    }

    public void setMatchOfferId(Integer matchOfferId) {
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
