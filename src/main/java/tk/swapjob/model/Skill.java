package tk.swapjob.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Skills")
public class Skill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 300, nullable = false)
    private String description;

    //region Constructors
    public Skill(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Skill() {
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
