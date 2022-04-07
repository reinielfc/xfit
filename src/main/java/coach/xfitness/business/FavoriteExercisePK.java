package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class FavoriteExercisePK implements Serializable {

    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "exerciseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    // #region boilerplate
    public FavoriteExercisePK() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FavoriteExercisePK))
            return false;
        FavoriteExercisePK that = (FavoriteExercisePK) o;
        return userId == that.userId
                && exerciseId == that.exerciseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId);
    }

    // #endregion boilerplate
}
