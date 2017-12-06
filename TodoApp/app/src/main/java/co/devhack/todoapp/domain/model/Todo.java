package co.devhack.todoapp.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by krlosf on 5/12/17.
 */

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String description;
    @ColumnInfo(name = "finish_date")
    private Date finishDate;
    private Boolean finished;
    private String image;
    private int color;

    /**
     * Constructor para crear todos
     * @param description
     * @param finishDate
     * @param finished
     * @param image
     * @param color
     */
    public Todo(String description, Date finishDate, Boolean finished, String image, int color) {
        this.description = description;
        this.finishDate = finishDate;
        this.finished = finished;
        this.image = image;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
