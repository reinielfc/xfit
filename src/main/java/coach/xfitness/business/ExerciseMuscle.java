package coach.xfitness.business;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
public class ExerciseMuscle {

    @EmbeddedId
    private ExerciseMusclePK id;

    @Basic
    @Column(name = "isSecondary", nullable = false)
    private int isSecondary;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @MapsId("muscleId")
    @JoinColumn(name = "muscleId", referencedColumnName = "id", nullable = false)
    private Muscle muscleByMuscleId;

    // #region boilerplate
    public ExerciseMuscle() {
    }

    public ExerciseMusclePK getId() {
        return id;
    }

    public void setId(ExerciseMusclePK id) {
        this.id = id;
    }

    public int getIsSecondary() {
        return isSecondary;
    }

    public void setIsSecondary(int isSecondary) {
        this.isSecondary = isSecondary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExerciseMuscle))
            return false;
        ExerciseMuscle that = (ExerciseMuscle) o;
        return isSecondary == that.isSecondary
                && Objects.equals(id, that.id)
                && Objects.equals(exerciseByExerciseId, that.exerciseByExerciseId)
                && Objects.equals(muscleByMuscleId, that.muscleByMuscleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSecondary, exerciseByExerciseId, muscleByMuscleId);
    }

    public Exercise getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(Exercise exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }

    public Muscle getMuscleByMuscleId() {
        return muscleByMuscleId;
    }

    public void setMuscleByMuscleId(Muscle muscleByMuscleId) {
        this.muscleByMuscleId = muscleByMuscleId;
    }

    // #endregion boilerplate
}
