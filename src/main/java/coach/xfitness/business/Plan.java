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
@IdClass(PlanPK.class)
public class Plan {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userId", nullable = false)
    private int userId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exerciseId", nullable = false)
    private int exerciseId;

    @Basic
    @Column(name = "dayOfWeek", nullable = false)
    private byte dayOfWeek;

    @Basic
    @Column(name = "position", nullable = false)
    private int position;

    @Basic
    @Column(name = "sets")
    private Short sets;

    @Basic
    @Column(name = "reps")
    private Short reps;

    @Basic
    @Column(name = "weight")
    private Short weight;

    @Basic
    @Column(name = "isDone")
    private Byte isDone;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    // #region boilerplate

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

    public byte getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Short getSets() {
        return sets;
    }

    public void setSets(Short sets) {
        this.sets = sets;
    }

    public Short getReps() {
        return reps;
    }

    public void setReps(Short reps) {
        this.reps = reps;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Byte getIsDone() {
        return isDone;
    }

    public void setIsDone(Byte isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Plan plan = (Plan) o;

        if (userId != plan.userId)
            return false;
        if (exerciseId != plan.exerciseId)
            return false;
        if (dayOfWeek != plan.dayOfWeek)
            return false;
        if (position != plan.position)
            return false;
        if (!Objects.equals(sets, plan.sets))
            return false;
        if (!Objects.equals(reps, plan.reps))
            return false;
        if (!Objects.equals(weight, plan.weight))
            return false;
        return Objects.equals(isDone, plan.isDone);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + exerciseId;
        result = 31 * result + (int) dayOfWeek;
        result = 31 * result + position;
        result = 31 * result + (sets != null ? sets.hashCode() : 0);
        result = 31 * result + (reps != null ? reps.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (isDone != null ? isDone.hashCode() : 0);
        return result;
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
