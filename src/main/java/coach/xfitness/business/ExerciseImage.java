package coach.xfitness.business;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class ExerciseImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "exerciseState", nullable = false, length = 32)
    private String exerciseState;
    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;
    @ManyToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id", nullable = false)
    private Image imageByImageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseState() {
        return exerciseState;
    }

    public void setExerciseState(String exerciseState) {
        this.exerciseState = exerciseState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseImage that = (ExerciseImage) o;

        if (id != that.id) return false;
        return Objects.equals(exerciseState, that.exerciseState);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (exerciseState != null ? exerciseState.hashCode() : 0);
        return result;
    }

    public Exercise getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(Exercise exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }

    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }
}
