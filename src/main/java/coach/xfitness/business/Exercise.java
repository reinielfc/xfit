package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = "Exercise.selectAll", query = "SELECT e FROM Exercise e"),
        @NamedQuery(name = "Exercise.selectByName", query = "SELECT e FROM Exercise e WHERE e.name = :name"),
        @NamedQuery(name = "Exercise.selectDistinctTypes", query = "SELECT DISTINCT e.type FROM Exercise e")
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
        return id == exercise.id
                && Objects.equals(name, exercise.name)
                && Objects.equals(title, exercise.title)
                && Objects.equals(primer, exercise.primer)
                && Objects.equals(type, exercise.type)
                && Objects.equals(steps, exercise.steps)
                && Objects.equals(tips, exercise.tips)
                && Objects.equals(links, exercise.links)
                && Objects.equals(equipment, exercise.equipment)
                && Objects.equals(exerciseImagesById, exercise.exerciseImagesById)
                && Objects.equals(exerciseMusclesById, exercise.exerciseMusclesById)
                && Objects.equals(favoritedBy, exercise.favoritedBy)
                && Objects.equals(plansById, exercise.plansById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, primer, type, steps, tips, links, equipment, exerciseImagesById,
                exerciseMusclesById, favoritedBy, plansById);
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

    private Collection<Muscle> getMuscles(boolean isSecondary) {
        return this.exerciseMusclesById
                .stream()
                .filter(em -> em.getIsSecondary() == (isSecondary ? 1 : 0))
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
        return this.steps.lines().collect(Collectors.toList());
    }

    public Collection<String> getTipsList() {
        return this.tips.lines().collect(Collectors.toList());
    }

}
