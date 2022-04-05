package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FavoriteExercisePK implements Serializable {
    @Column(name = "userId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    @Column(name = "exerciseId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteExercisePK that = (FavoriteExercisePK) o;

        if (userId != that.userId) return false;
        return exerciseId == that.exerciseId;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + exerciseId;
        return result;
    }
}
