package coach.xfitness.business;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExerciseMuscleKey implements Serializable {

    @Column(name = "exerciseId")
    private int exerciseId;

    @Column(name = "muscleId")
    private int muscleId;

    public ExerciseMuscleKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseMuscleKey that = (ExerciseMuscleKey) o;
        return exerciseId == that.exerciseId && muscleId == that.muscleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, muscleId);
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
}
