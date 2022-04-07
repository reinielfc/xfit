package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Muscle.selectAll", query = "SELECT m FROM Muscle m")
public class Muscle {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @OneToMany(mappedBy = "muscleByMuscleId")
    private Collection<ExerciseMuscle> exerciseMusclesById;

    // #region boilerplate

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Muscle))
            return false;
        Muscle muscle = (Muscle) o;
        return id == muscle.id
                && Objects.equals(name, muscle.name)
                && Objects.equals(exerciseMusclesById, muscle.exerciseMusclesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, exerciseMusclesById);
    }

    public Collection<ExerciseMuscle> getExerciseMusclesById() {
        return exerciseMusclesById;
    }

    public void setExerciseMusclesById(Collection<ExerciseMuscle> exerciseMusclesById) {
        this.exerciseMusclesById = exerciseMusclesById;
    }

    // #endregion boilerplate
}
