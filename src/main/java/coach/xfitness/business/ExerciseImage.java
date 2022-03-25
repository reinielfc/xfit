package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ExerciseImage.findAll", query = "SELECT e FROM ExerciseImage e")
public class ExerciseImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private String exerciseState;

	@ManyToOne
	@JoinColumn(name = "exerciseID")
	private Exercise exercise;

	@ManyToOne
	@JoinColumn(name = "imageID")
	private Image image;

	public ExerciseImage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExerciseState() {
		return this.exerciseState;
	}

	public void setExerciseState(String exerciseState) {
		this.exerciseState = exerciseState;
	}

	public Exercise getExercise() {
		return this.exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}