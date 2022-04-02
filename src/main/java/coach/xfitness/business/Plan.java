package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long planID;
    private long userID;
    private long exerciseID;
    private String dayOfWeek;
    private int sets;
    private int reps;
    private int time;

    public Plan() {
        //remember missing fields experienceID and categoryID in the constructor.
        this.dayOfWeek= "";
        this.sets = 0;
        this.reps = 0;
        this.time = 0;
    }
    public long getPlanID() {
        return planID;
    }

    public void setPlanID(long planID) {
        this.planID = planID;
    }
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;

    }public long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(long exerciseID) {
        this.exerciseID = exerciseID;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }
    public int getSets() {
        return sets;
    }

    public void setSets(int sets){
        this.sets = sets;
    }
    public int getReps() {
        return reps;
    }

    public void setReps(int reps){
        this.reps = reps;
    }
    public int getTime() {
        return time;
    }

    public void setTime(int time){
        this.time = time;
    }
    
}
