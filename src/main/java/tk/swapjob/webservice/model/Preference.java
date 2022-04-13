package tk.swapjob.webservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Preferences")
public class Preference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Float lowThreshold;
    private Float highThreshold;
    private Float value;

    //region Constructor
    public Preference() {
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

    public Float getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(Float lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public Float getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(Float highThreshold) {
        this.highThreshold = highThreshold;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    //endregion
}
