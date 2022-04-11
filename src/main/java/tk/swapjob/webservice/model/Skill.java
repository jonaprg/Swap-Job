package tk.swapjob.webservice.model;

public class Skill {
    private Integer id;
    private String title;
    private String description;

    //region Constructors
    public Skill(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}