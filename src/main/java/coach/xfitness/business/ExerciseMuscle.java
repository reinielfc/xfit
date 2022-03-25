package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ExerciseMuscle.findAll", query = "SELECT e FROM ExerciseMuscle e")
public class ExerciseMuscle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private int isSecondary;

	@ManyToOne
	@JoinColumn(name = "exerciseID")
	private Exercise exercise;

	@ManyToOne
	@JoinColumn(name = "muscleID")
	private Muscle muscle;

	public ExerciseMuscle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsSecondary() {
		return this.isSecondary;
	}

	public void setIsSecondary(int isSecondary) {
		this.isSecondary = isSecondary;
	}

	public Exercise getExercise() {
		return this.exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Muscle getMuscle() {
		return this.muscle;
	}

	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}

}