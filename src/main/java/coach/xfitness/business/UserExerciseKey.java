package coach.xfitness.business;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserExerciseKey implements Serializable {

    @Column(name = "userId")
    private int userId;

    @Column(name = "exerciseId")
    private int exerciseId;

    public UserExerciseKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExerciseKey that = (UserExerciseKey) o;
        return userId == that.userId && exerciseId == that.exerciseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
