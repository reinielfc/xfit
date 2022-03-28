package coach.xfitness.business;

import jakarta.persistence.*;

@Entity
public class ExerciseMuscle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "isSecondary", nullable = false)
    private int isSecondary;
    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;
    @ManyToOne
    @JoinColumn(name = "muscleId", referencedColumnName = "id", nullable = false)
    private Muscle muscleByMuscleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseMuscle that = (ExerciseMuscle) o;

        if (id != that.id) return false;
        return isSecondary == that.isSecondary;
    }

    @Override
    public int hashCode() {
        int result = id;
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
}
