package coach.xfitness.business;

import java.util.Objects;

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
@IdClass(ExerciseImagePK.class)
public class ExerciseImage {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exerciseId", nullable = false)
    private int exerciseId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "imageId", nullable = false)
    private int imageId;

    @Basic
    @Column(name = "exerciseState", nullable = false, length = 32)
    private String exerciseState;

    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id", nullable = false)
    private Image imageByImageId;

    // #region boilerplate

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getExerciseState() {
        return exerciseState;
    }

    public void setExerciseState(String exerciseState) {
        this.exerciseState = exerciseState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ExerciseImage that = (ExerciseImage) o;

        if (exerciseId != that.exerciseId)
            return false;
        if (imageId != that.imageId)
            return false;
        return Objects.equals(exerciseState, that.exerciseState);
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + imageId;
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

    // #endregion boilerplate
}
