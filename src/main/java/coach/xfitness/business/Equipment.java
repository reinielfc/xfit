package coach.xfitness.business;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (id != equipment.id) return false;
        return Objects.equals(name, equipment.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Collection<Exercise> exerciseEquipmentsById) {
        this.exercises = exerciseEquipmentsById;
    }


    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> userEquipments) {
        this.users = userEquipments;
    }
}
