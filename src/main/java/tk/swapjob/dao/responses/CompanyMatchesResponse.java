package tk.swapjob.dao.responses;

import tk.swapjob.model.MatchOffer;
import tk.swapjob.model.User;

import java.util.ArrayList;
import java.util.List;

public class CompanyMatchesResponse {
    private Long offerId;
    private List<User> userList;

    public CompanyMatchesResponse(MatchOffer matchOffer) {
        this.offerId = matchOffer.getOffer().getId();
        this.userList = new ArrayList<>();
    }

    public CompanyMatchesResponse() {
        this.userList = new ArrayList<>();
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
