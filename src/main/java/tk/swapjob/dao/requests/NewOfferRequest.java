package tk.swapjob.dao.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tk.swapjob.model.Company;

public class NewOfferRequest {

    private String description;
    private boolean is_remote;
    private float salary;
    private String title;

    public String getDescription() {return description;}
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean getIs_remote() {
        return is_remote;
    }

    public void setIs_remote(boolean is_remote) {
        this.is_remote = is_remote;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @JsonIgnore
    public boolean isValid() {
        return description != null && salary != 0.0f && title != null;
    }

    @JsonIgnore
    public boolean isInvalid() {
        return !isValid();
    }
}
