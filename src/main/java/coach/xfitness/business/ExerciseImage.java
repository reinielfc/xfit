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
public class ExerciseImage {

    @EmbeddedId
    private ExerciseImagePK id;

    @Basic
    @Column(name = "exerciseState", nullable = false, length = 32)
    private String exerciseState;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "imageId", referencedColumnName = "id", nullable = false)
    private Image imageByImageId;

    // #region boilerplate
    public ExerciseImage() {
    }

    public ExerciseImagePK getId() {
        return id;
    }

    public void setId(ExerciseImagePK id) {
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
        if (this == o)
            return true;
        if (!(o instanceof ExerciseImage))
            return false;
        ExerciseImage that = (ExerciseImage) o;
        return Objects.equals(id, that.id)
                && Objects.equals(exerciseState, that.exerciseState)
                && Objects.equals(exerciseByExerciseId, that.exerciseByExerciseId)
                && Objects.equals(imageByImageId, that.imageByImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseState, exerciseByExerciseId, imageByImageId);
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
