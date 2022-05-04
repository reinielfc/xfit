package coach.xfitness.business;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class FavoriteExercise {

    @EmbeddedId
    private FavoriteExercisePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exerciseId")
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    // #region boilerplate
    public FavoriteExercise() {
    }

    public FavoriteExercisePK getId() {
        return id;
    }

    public void setId(FavoriteExercisePK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FavoriteExercise))
            return false;
        FavoriteExercise that = (FavoriteExercise) o;
        return Objects.equals(id, that.id)
                && Objects.equals(userByUserId, that.userByUserId)
                && Objects.equals(exerciseByExerciseId, that.exerciseByExerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userByUserId, exerciseByExerciseId);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Exercise getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(Exercise exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }

    // #endregion boilerplate
}
