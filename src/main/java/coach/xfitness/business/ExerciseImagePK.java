package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ExerciseImagePK implements Serializable {

    @Column(name = "exerciseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    @Column(name = "imageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    // #region boilerplate
    public ExerciseImagePK() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExerciseImagePK))
            return false;
        ExerciseImagePK that = (ExerciseImagePK) o;
        return exerciseId == that.exerciseId
                && imageId == that.imageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, imageId);
    }

    // #endregion boilerplate
}
