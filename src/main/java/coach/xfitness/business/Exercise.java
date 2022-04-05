package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Exercise {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "userId")
    private Integer userId;

    @Basic
    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Basic
    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Basic
    @Column(name = "primer", length = 512)
    private String primer;

    @Basic
    @Column(name = "type", length = 32)
    private String type;

    @Basic
    @Column(name = "steps", length = -1)
    private String steps;

    @Basic
    @Column(name = "tips", length = -1)
    private String tips;

    @Basic
    @Column(name = "links", length = -1)
    private String links;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userByUserId;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseEquipment> exerciseEquipmentsById;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseImage> exerciseImagesById;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseMuscle> exerciseMusclesById;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<FavoriteExercise> favoriteExercisesById;
    
    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<Plan> plansById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimer() {
        return primer;
    }

    public void setPrimer(String primer) {
        this.primer = primer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (id != exercise.id) return false;
        if (!Objects.equals(userId, exercise.userId)) return false;
        if (!Objects.equals(name, exercise.name)) return false;
        if (!Objects.equals(title, exercise.title)) return false;
        if (!Objects.equals(primer, exercise.primer)) return false;
        if (!Objects.equals(type, exercise.type)) return false;
        if (!Objects.equals(steps, exercise.steps)) return false;
        if (!Objects.equals(tips, exercise.tips)) return false;
        return Objects.equals(links, exercise.links);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (primer != null ? primer.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        result = 31 * result + (tips != null ? tips.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Collection<ExerciseEquipment> getExerciseEquipmentsById() {
        return exerciseEquipmentsById;
    }

    public void setExerciseEquipmentsById(Collection<ExerciseEquipment> exerciseEquipmentsById) {
        this.exerciseEquipmentsById = exerciseEquipmentsById;
    }

    public Collection<ExerciseImage> getExerciseImagesById() {
        return exerciseImagesById;
    }

    public void setExerciseImagesById(Collection<ExerciseImage> exerciseImagesById) {
        this.exerciseImagesById = exerciseImagesById;
    }

    public Collection<ExerciseMuscle> getExerciseMusclesById() {
        return exerciseMusclesById;
    }

    public void setExerciseMusclesById(Collection<ExerciseMuscle> exerciseMusclesById) {
        this.exerciseMusclesById = exerciseMusclesById;
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
}
