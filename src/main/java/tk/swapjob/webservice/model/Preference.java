package tk.swapjob.webservice.model;

public class Preference {
    private Integer id;
    private String title;
    private Float lowThreshold;
    private Float highThreshold;
    private Float value;

    //region Constructor
    public Preference(Integer id, String title, Float lowThreshold, Float highThreshold, Float value) {
        this.id = id;
        this.title = title;
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
        this.value = value;
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
