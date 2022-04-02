package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExerciseImageKey implements Serializable {

    @Column(name = "exerciseId")
    private int exerciseId;

    @Column(name = "imageId")
    private int imageId;

    public ExerciseImageKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseImageKey that = (ExerciseImageKey) o;
        return exerciseId == that.exerciseId && imageId == that.imageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, imageId);
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
}
