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
@NamedQuery(name = "User.selectByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
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

    @Basic
    @Column(name = "accessToken")
    private String accessToken;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Exercise> exercisesById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<FavoriteExercise> favoriteExercisesById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Plan> plansById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserEquipment> userEquipmentsById;

    // #region boilerplate
    public User() {
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(accessToken, user.accessToken)
                && Objects.equals(exercisesById, user.exercisesById)
                && Objects.equals(favoriteExercisesById, user.favoriteExercisesById)
                && Objects.equals(plansById, user.plansById)
                && Objects.equals(userEquipmentsById, user.userEquipmentsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, accessToken, exercisesById, favoriteExercisesById, plansById,
                userEquipmentsById);
    }

    public Collection<Exercise> getExercisesById() {
        return exercisesById;
    }

    public void setExercisesById(Collection<Exercise> exercisesById) {
        this.exercisesById = exercisesById;
    }

    public Collection<FavoriteExercise> getFavoriteExercisesById() {
        return favoriteExercisesById;
    }

    public void setFavoriteExercisesById(Collection<FavoriteExercise> favoriteExercisesById) {
        this.favoriteExercisesById = favoriteExercisesById;
    }

    public Collection<Plan> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<Plan> plansById) {
        this.plansById = plansById;
    }

    public Collection<UserEquipment> getUserEquipmentsById() {
        return userEquipmentsById;
    }

    public void setUserEquipmentsById(Collection<UserEquipment> userEquipmentsById) {
        this.userEquipmentsById = userEquipmentsById;
    }

    // #endregion boilerplate
}
