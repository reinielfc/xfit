package coach.xfitness.business;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e")
public class Exercise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Lob
	private String links;

	private String primer;

	@Lob
	private String steps;

	@Lob
	private String tips;

	private String title;

	private String type;

	@OneToMany(mappedBy = "exercise")
	private List<ExerciseEquipment> exerciseEquipments;

	@OneToMany(mappedBy = "exercise")
	private List<ExerciseImage> exerciseImages;

	@OneToMany(mappedBy = "exercise")
	private List<ExerciseMuscle> exerciseMuscles;

	@OneToMany(mappedBy = "exercise")
	private List<FavoriteExercise> favoriteExercises;

	@OneToMany(mappedBy = "exercise")
	private List<Plan> plans;

	public Exercise() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinks() {
		return this.links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getPrimer() {
		return this.primer;
	}

	public void setPrimer(String primer) {
		this.primer = primer;
	}

	public String getSteps() {
		return this.steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getTips() {
		return this.tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ExerciseEquipment> getExerciseEquipments() {
		return this.exerciseEquipments;
	}

	public void setExerciseEquipments(List<ExerciseEquipment> exerciseEquipments) {
		this.exerciseEquipments = exerciseEquipments;
	}

	public ExerciseEquipment addExerciseEquipment(ExerciseEquipment exerciseEquipment) {
		getExerciseEquipments().add(exerciseEquipment);
		exerciseEquipment.setExercise(this);

		return exerciseEquipment;
	}

	public ExerciseEquipment removeExerciseEquipment(ExerciseEquipment exerciseEquipment) {
		getExerciseEquipments().remove(exerciseEquipment);
		exerciseEquipment.setExercise(null);

		return exerciseEquipment;
	}

	public List<ExerciseImage> getExerciseImages() {
		return this.exerciseImages;
	}

	public void setExerciseImages(List<ExerciseImage> exerciseImages) {
		this.exerciseImages = exerciseImages;
	}

	public ExerciseImage addExerciseImage(ExerciseImage exerciseImage) {
		getExerciseImages().add(exerciseImage);
		exerciseImage.setExercise(this);

		return exerciseImage;
	}

	public ExerciseImage removeExerciseImage(ExerciseImage exerciseImage) {
		getExerciseImages().remove(exerciseImage);
		exerciseImage.setExercise(null);

		return exerciseImage;
	}

	public List<ExerciseMuscle> getExerciseMuscles() {
		return this.exerciseMuscles;
	}

	public void setExerciseMuscles(List<ExerciseMuscle> exerciseMuscles) {
		this.exerciseMuscles = exerciseMuscles;
	}

	public ExerciseMuscle addExerciseMuscle(ExerciseMuscle exerciseMuscle) {
		getExerciseMuscles().add(exerciseMuscle);
		exerciseMuscle.setExercise(this);

		return exerciseMuscle;
	}

	public ExerciseMuscle removeExerciseMuscle(ExerciseMuscle exerciseMuscle) {
		getExerciseMuscles().remove(exerciseMuscle);
		exerciseMuscle.setExercise(null);

		return exerciseMuscle;
	}

	public List<FavoriteExercise> getFavoriteExercises() {
		return this.favoriteExercises;
	}

	public void setFavoriteExercises(List<FavoriteExercise> favoriteExercises) {
		this.favoriteExercises = favoriteExercises;
	}

	public FavoriteExercise addFavoriteExercis(FavoriteExercise favoriteExercis) {
		getFavoriteExercises().add(favoriteExercis);
		favoriteExercis.setExercise(this);

		return favoriteExercis;
	}

	public FavoriteExercise removeFavoriteExercis(FavoriteExercise favoriteExercis) {
		getFavoriteExercises().remove(favoriteExercis);
		favoriteExercis.setExercise(null);

		return favoriteExercis;
	}

	public List<Plan> getPlans() {
		return this.plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	public Plan addPlan(Plan plan) {
		getPlans().add(plan);
		plan.setExercise(this);

		return plan;
	}

	public Plan removePlan(Plan plan) {
		getPlans().remove(plan);
		plan.setExercise(null);

		return plan;
	}

}