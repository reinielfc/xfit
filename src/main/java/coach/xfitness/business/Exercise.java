package coach.xfitness.business;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseID;
    private Long experienceID;
    private Long categoryID;
    private String name;
    private String description;

    public Exercise() {
        //remember missing fields experienceID and categoryID in the constructor.
        this.name= "";
        this.description ="";
    }
    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }
    public Long getExperienceID() {
        return experienceID;
    }

    public void setExperienceID(Long experienceID) {
        this.experienceID = experienceID;

    }public Long getCategoryID() {
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

    public void setDescription(String description){
        this.description = description;
    }
    
}
