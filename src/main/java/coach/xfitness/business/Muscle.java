package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
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
        if (o == null || getClass() != o.getClass())
            return false;

        Muscle muscle = (Muscle) o;

        if (id != muscle.id)
            return false;
        return Objects.equals(name, muscle.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<ExerciseMuscle> getExerciseMusclesById() {
        return exerciseMusclesById;
    }

    public void setExerciseMusclesById(Collection<ExerciseMuscle> exerciseMusclesById) {
        this.exerciseMusclesById = exerciseMusclesById;
    }

    // #endregion boilerplate
}
