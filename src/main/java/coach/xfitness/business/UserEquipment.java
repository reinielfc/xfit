package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "UserEquipment.findAll", query = "SELECT u FROM UserEquipment u")
public class UserEquipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "equipmentID")
	private Equipment equipment;

	public UserEquipment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}