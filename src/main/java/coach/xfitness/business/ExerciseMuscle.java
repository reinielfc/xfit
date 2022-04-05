package coach.xfitness.business;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ExerciseMusclePK.class)
public class ExerciseMuscle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exerciseId", nullable = false)
    private int exerciseId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "muscleId", nullable = false)
    private int muscleId;

    @Basic
    @Column(name = "isSecondary", nullable = false)
    private int isSecondary;

    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @JoinColumn(name = "muscleId", referencedColumnName = "id", nullable = false)
    private Muscle muscleByMuscleId;

    // #region boilerplate

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
        if (o == null || getClass() != o.getClass())
            return false;

        ExerciseMuscle that = (ExerciseMuscle) o;

        if (exerciseId != that.exerciseId)
            return false;
        if (muscleId != that.muscleId)
            return false;
        return isSecondary == that.isSecondary;
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + muscleId;
        result = 31 * result + isSecondary;
        return result;
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
