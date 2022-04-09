package coach.xfitness.business;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQuery(name = "Equipment.selectAll", query = "SELECT e FROM Equipment e")
public class Equipment {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @ManyToMany(mappedBy = "equipment")
    private Collection<Exercise> exercises;
    
    @ManyToMany(mappedBy = "equipment")
    private Collection<User> users;

    public Equipment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return id == equipment.id && Objects.equals(name, equipment.name) && Objects.equals(exercises, equipment.exercises) && Objects.equals(users, equipment.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, exercises, users);
    }

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

    public Collection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Collection<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
