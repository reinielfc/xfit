package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = "Exercise.selectAll", query = "SELECT e FROM Exercise e"),
        @NamedQuery(name = "Exercise.selectByName", query = "SELECT e FROM Exercise e WHERE e.name = :name"),
        @NamedQuery(name = "Exercise.selectDistinctTypes", query = "SELECT DISTINCT e.type FROM Exercise e"),
        @NamedQuery(name = "Exercise.selectAllAvailable", query = "SELECT e FROM Exercise e WHERE e.userByUserId = :userByUserId OR e.userByUserId = null")
})
public class Exercise {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

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

    @OneToMany(mappedBy = "exerciseByExerciseId", cascade = CascadeType.ALL)
    private Collection<ExerciseEquipment> exerciseEquipmentsById;

    @OneToMany(mappedBy = "exerciseByExerciseId", cascade = CascadeType.ALL)
    private Collection<ExerciseImage> exerciseImagesById;

    @OneToMany(mappedBy = "exerciseByExerciseId", cascade = CascadeType.ALL)
    private Collection<ExerciseMuscle> exerciseMusclesById;

    @OneToMany(mappedBy = "exerciseByExerciseId", cascade = CascadeType.ALL)
    private Collection<FavoriteExercise> favoriteExercisesById;

    @OneToMany(mappedBy = "exerciseByExerciseId", cascade = CascadeType.ALL)
    private Collection<Plan> plansById;

    // #region boilerplate
    public Exercise() {
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
        if (this == o)
            return true;
        if (!(o instanceof Exercise))
            return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id
                && Objects.equals(name, exercise.name)
                && Objects.equals(title, exercise.title)
                && Objects.equals(primer, exercise.primer)
                && Objects.equals(type, exercise.type)
                && Objects.equals(steps, exercise.steps)
                && Objects.equals(tips, exercise.tips)
                && Objects.equals(links, exercise.links)
                && Objects.equals(userByUserId, exercise.userByUserId)
                && Objects.equals(exerciseEquipmentsById, exercise.exerciseEquipmentsById)
                && Objects.equals(exerciseImagesById, exercise.exerciseImagesById)
                && Objects.equals(exerciseMusclesById, exercise.exerciseMusclesById)
                && Objects.equals(favoriteExercisesById, exercise.favoriteExercisesById)
                && Objects.equals(plansById, exercise.plansById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, primer, type, steps, tips, links, userByUserId,
                exerciseEquipmentsById, exerciseImagesById, exerciseMusclesById, favoriteExercisesById, plansById);
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

    // #endregion boilerplate

    public Collection<Equipment> getEquipment() {
        return exerciseEquipmentsById.stream()
                .map(ExerciseEquipment::getEquipmentByEquipmentId)
                .collect(Collectors.toList());
    }

    private Collection<Muscle> getMuscles(boolean isSecondary) {
        int isSecondaryInt = isSecondary ? 1 : 0;
        return exerciseMusclesById.stream()
                .filter(em -> em.getIsSecondary() == isSecondaryInt)
                .map(ExerciseMuscle::getMuscleByMuscleId)
                .collect(Collectors.toList());
    }

    public Collection<Muscle> getPrimaryMuscles() {
        return getMuscles(false);
    }

    public Collection<Muscle> getSecondaryMuscles() {
        return getMuscles(true);
    }

    public Collection<String> getStepsList() {
        return steps.lines().collect(Collectors.toList());
    }

    public Collection<String> getTipsList() {
        return tips.lines().collect(Collectors.toList());
    }

    public Collection<String> getLinksList() {
        return links.lines().collect(Collectors.toList());
    }

    public boolean isFavoritedBy(User user) {
        return favoriteExercisesById.stream()
                .map(FavoriteExercise::getUserByUserId)
                .anyMatch(u -> u.equals(user));
                
                
    }

}
