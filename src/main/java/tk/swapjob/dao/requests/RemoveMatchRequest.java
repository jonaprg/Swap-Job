package tk.swapjob.dao.requests;

public class RemoveMatchRequest {
    private Integer userId;
    private Long offerId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
}
