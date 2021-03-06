package coach.xfitness.business;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Exercise.selectByPositionInDay", query = "SELECT p FROM Plan p WHERE p.dayOfWeek = :dayOfWeek AND p.position = :position")
public class Plan {

    private static final List<DayOfWeek> DAYS_OF_WEEK = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY);

    @EmbeddedId
    private PlanPK id;

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
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    public Plan(User user, Exercise exercise) {
        this.userByUserId = user;
        this.exerciseByExerciseId = exercise;

        this.id = new PlanPK(userByUserId.getId(), exerciseByExerciseId.getId());
    }

    // #region boilerplate
    public Plan() {
    }

    public PlanPK getId() {
        return id;
    }

    public void setId(PlanPK id) {
        this.id = id;
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
        if (!(o instanceof Plan))
            return false;
        Plan plan = (Plan) o;
        return dayOfWeek == plan.dayOfWeek
                && position == plan.position
                && Objects.equals(id, plan.id)
                && Objects.equals(sets, plan.sets)
                && Objects.equals(reps, plan.reps)
                && Objects.equals(weight, plan.weight)
                && Objects.equals(isDone, plan.isDone)
                && Objects.equals(userByUserId, plan.userByUserId)
                && Objects.equals(exerciseByExerciseId, plan.exerciseByExerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, position, sets, reps, weight, isDone, userByUserId, exerciseByExerciseId);
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

    /**
     * Get the short name of the day of the week in lower case.
     * 
     * @return The day of the week in short form.
     */
    public String getDayOfWeekShortName() {
        return getDayOfWeekObject().getDisplayName(TextStyle.SHORT, Locale.getDefault()).toLowerCase();
    }

    /**
     * Return the DayOfWeek object that corresponds to the day of week integer stored
     * in this object.
     * 
     * @return A DayOfWeek object
     */
    public DayOfWeek getDayOfWeekObject() {
        return DAYS_OF_WEEK.get(dayOfWeek);
    }

    /**
     * Given a day of the week, return the index of that day in the week.
     * 
     * @param dayOfWeek The day of the week to get the index for.
     * @return The index of the day of the week.
     */
    public static byte getDayOfWeek(String dayOfWeek) {
        String shortName;
        
        for (int i = 0; i < DAYS_OF_WEEK.size(); i++) {
            shortName = DAYS_OF_WEEK.get(i)
                    .getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    .toLowerCase();
            
            if (shortName.equals(dayOfWeek)) {
                return Integer.valueOf(i).byteValue();
            }
        }

        return 0;
    }

}
