package coach.xfitness.business;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class ExerciseMuscle {

    @EmbeddedId
    private ExerciseMuscleKey id;

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

    public ExerciseMuscle() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseMuscle that = (ExerciseMuscle) o;
        return isSecondary == that.isSecondary && Objects.equals(id, that.id) && Objects.equals(exerciseByExerciseId, that.exerciseByExerciseId) && Objects.equals(muscleByMuscleId, that.muscleByMuscleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSecondary, exerciseByExerciseId, muscleByMuscleId);
    }

    public ExerciseMuscleKey getId() {
        return id;
    }

    public void setId(ExerciseMuscleKey id) {
        this.id = id;
    }

    public int getIsSecondary() {
        return isSecondary;
    }

    public void setIsSecondary(int isSecondary) {
        this.isSecondary = isSecondary;
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
}
