package coach.xfitness.business;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQuery(name = "Exercise.selectAll", query = "SELECT e FROM Exercise e")
@NamedQuery(name = "Exercise.selectById", query = "SELECT e FROM Exercise e WHERE e.id = :id")
public class Exercise {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

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

    @ManyToMany
    @JoinTable(name = "ExerciseEquipment", joinColumns = @JoinColumn(name = "exerciseId"), inverseJoinColumns = @JoinColumn(name = "equipmentId"))
    private Collection<Equipment> equipment;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseImage> exerciseImagesById;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<ExerciseMuscle> exerciseMusclesById;

    @ManyToMany(mappedBy = "favoriteExercises")
    private Collection<User> favoritedBy;

    @OneToMany(mappedBy = "exerciseByExerciseId")
    private Collection<Plan> plansById;

    public Exercise() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id && Objects.equals(title, exercise.title) && Objects.equals(primer, exercise.primer)
                && Objects.equals(type, exercise.type) && Objects.equals(steps, exercise.steps)
                && Objects.equals(tips, exercise.tips) && Objects.equals(links, exercise.links)
                && Objects.equals(equipment, exercise.equipment)
                && Objects.equals(exerciseImagesById, exercise.exerciseImagesById)
                && Objects.equals(exerciseMusclesById, exercise.exerciseMusclesById)
                && Objects.equals(favoritedBy, exercise.favoritedBy) && Objects.equals(plansById, exercise.plansById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, primer, type, steps, tips, links, equipment, exerciseImagesById,
                exerciseMusclesById, favoritedBy, plansById);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Collection<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Collection<Equipment> equipment) {
        this.equipment = equipment;
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

    public Collection<User> getFavoritedBy() {
        return favoritedBy;
    }

    public void setFavoritedBy(Collection<User> favoritedBy) {
        this.favoritedBy = favoritedBy;
    }

    public Collection<Plan> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<Plan> plansById) {
        this.plansById = plansById;
    }
}
