package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ExerciseEquipment.findAll", query = "SELECT e FROM ExerciseEquipment e")
public class ExerciseEquipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "exerciseID")
	private Exercise exercise;

	@ManyToOne
	@JoinColumn(name = "equipmentID")
	private Equipment equipment;

	public ExerciseEquipment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exercise getExercise() {
		return this.exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}