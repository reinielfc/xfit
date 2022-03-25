package coach.xfitness.business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e")
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private String name;

	@OneToMany(mappedBy = "equipment")
	private List<ExerciseEquipment> exerciseEquipments;

	@OneToMany(mappedBy = "equipment")
	private List<UserEquipment> userEquipments;

	public Equipment() {
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

	public List<ExerciseEquipment> getExerciseEquipments() {
		return this.exerciseEquipments;
	}

	public void setExerciseEquipments(List<ExerciseEquipment> exerciseEquipments) {
		this.exerciseEquipments = exerciseEquipments;
	}

	public ExerciseEquipment addExerciseEquipment(ExerciseEquipment exerciseEquipment) {
		getExerciseEquipments().add(exerciseEquipment);
		exerciseEquipment.setEquipment(this);

		return exerciseEquipment;
	}

	public ExerciseEquipment removeExerciseEquipment(ExerciseEquipment exerciseEquipment) {
		getExerciseEquipments().remove(exerciseEquipment);
		exerciseEquipment.setEquipment(null);

		return exerciseEquipment;
	}

	public List<UserEquipment> getUserEquipments() {
		return this.userEquipments;
	}

	public void setUserEquipments(List<UserEquipment> userEquipments) {
		this.userEquipments = userEquipments;
	}

	public UserEquipment addUserEquipment(UserEquipment userEquipment) {
		getUserEquipments().add(userEquipment);
		userEquipment.setEquipment(this);

		return userEquipment;
	}

	public UserEquipment removeUserEquipment(UserEquipment userEquipment) {
		getUserEquipments().remove(userEquipment);
		userEquipment.setEquipment(null);

		return userEquipment;
	}

}