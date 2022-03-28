package coach.xfitness.business;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
public class Plan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dayOfWeek", nullable = false)
    private int dayOfWeek;
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
    @Column(name = "duration")
    private Time duration;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (id != plan.id) return false;
        if (dayOfWeek != plan.dayOfWeek) return false;
        if (!Objects.equals(sets, plan.sets)) return false;
        if (!Objects.equals(reps, plan.reps)) return false;
        if (!Objects.equals(weight, plan.weight)) return false;
        return Objects.equals(duration, plan.duration);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dayOfWeek;
        result = 31 * result + (sets != null ? sets.hashCode() : 0);
        result = 31 * result + (reps != null ? reps.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
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
}
