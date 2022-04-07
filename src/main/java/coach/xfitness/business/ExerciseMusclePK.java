package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ExerciseMusclePK implements Serializable {

    @Column(name = "exerciseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    @Column(name = "muscleId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int muscleId;

    // #region boilerplate
    public ExerciseMusclePK() {
    }

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
        if (this == o)
            return true;
        if (!(o instanceof ExerciseMusclePK))
            return false;
        ExerciseMusclePK that = (ExerciseMusclePK) o;
        return exerciseId == that.exerciseId
                && muscleId == that.muscleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, muscleId);
    }

    // #endregion boilerplate
}
