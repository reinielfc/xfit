package coach.xfitness.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Exercise
 */
@Entity
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseID;
    private Long userID;
    private Long categoryID;
    private String name;
    private String description;
    private List<String> tags;

    public Exercise() {
        this.userID = -1L;
        this.name = "";
        this.description = "";
        this.tags = new ArrayList<>();
    }

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public void setTags(String [] tags) {
        this.tags = new ArrayList<>(Arrays.asList(tags));
    }
}