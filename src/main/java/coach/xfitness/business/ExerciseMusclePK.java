package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ExerciseMusclePK implements Serializable {
    @Column(name = "exerciseId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;
    
    @Column(name = "muscleId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int muscleId;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(int muscleId) {
        this.muscleId = muscleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseMusclePK that = (ExerciseMusclePK) o;

        if (exerciseId != that.exerciseId) return false;
        return muscleId == that.muscleId;
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + muscleId;
        return result;
    }
}
