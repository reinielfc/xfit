package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class PlanPK implements Serializable {

    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "exerciseId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    public PlanPK(int userId, int exerciseId) {
        this.userId = userId;
        this.exerciseId = exerciseId;
    }

    // #region boilerplate
    public PlanPK() {
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
        if (!(o instanceof PlanPK))
            return false;
        PlanPK planPK = (PlanPK) o;
        return userId == planPK.userId
                && exerciseId == planPK.exerciseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId);
    }

    // #endregion boilerplate
}
