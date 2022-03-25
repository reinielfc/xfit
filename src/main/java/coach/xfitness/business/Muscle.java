package coach.xfitness.business;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Muscle.findAll", query = "SELECT m FROM Muscle m")
public class Muscle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private String name;

	@OneToMany(mappedBy = "muscle")
	private List<ExerciseMuscle> exerciseMuscles;

	public Muscle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExerciseMuscle> getExerciseMuscles() {
		return this.exerciseMuscles;
	}

	public void setExerciseMuscles(List<ExerciseMuscle> exerciseMuscles) {
		this.exerciseMuscles = exerciseMuscles;
	}

	public ExerciseMuscle addExerciseMuscle(ExerciseMuscle exerciseMuscle) {
		getExerciseMuscles().add(exerciseMuscle);
		exerciseMuscle.setMuscle(this);

		return exerciseMuscle;
	}

	public ExerciseMuscle removeExerciseMuscle(ExerciseMuscle exerciseMuscle) {
		getExerciseMuscles().remove(exerciseMuscle);
		exerciseMuscle.setMuscle(null);

		return exerciseMuscle;
	}

}