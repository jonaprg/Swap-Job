package tk.swapjob.webservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "status_type")
public class Status implements Serializable {
    @Id
    private Integer id;
    private String title;

    //region Constructors
    public Status() {
    }
    //endregion

    //region Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //endregion
}
