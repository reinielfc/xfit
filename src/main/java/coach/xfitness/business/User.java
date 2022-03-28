package coach.xfitness.business;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Basic
    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "FavoriteExercise", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "exerciseId"))
    private Collection<Exercise> favoriteExercises;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Plan> plansById;

    @ManyToMany
    @JoinTable(name = "UserEquipment", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "equipmentId"))
    private Collection<Equipment> equipment;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (!Objects.equals(name, user.name))
            return false;
        if (!Objects.equals(email, user.email))
            return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public Collection<Exercise> getFavoriteExercises() {
        return favoriteExercises;
    }

    public void setFavoriteExercises(Collection<Exercise> favoriteExercises) {
        this.favoriteExercises = favoriteExercises;
    }

    public Collection<Plan> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<Plan> plansById) {
        this.plansById = plansById;
    }

    public Collection<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Collection<Equipment> userEquipmentsById) {
        this.equipment = userEquipmentsById;
    }
}
