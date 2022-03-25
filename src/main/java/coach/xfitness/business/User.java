package coach.xfitness.business;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private String email;

	private String name;

	private String password;

	@OneToMany(mappedBy = "user")
	private List<FavoriteExercise> favoriteExercises;

	@OneToMany(mappedBy = "user")
	private List<Plan> plans;

	@OneToMany(mappedBy = "user")
	private List<UserEquipment> userEquipments;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<FavoriteExercise> getFavoriteExercises() {
		return this.favoriteExercises;
	}

	public void setFavoriteExercises(List<FavoriteExercise> favoriteExercises) {
		this.favoriteExercises = favoriteExercises;
	}

	public FavoriteExercise addFavoriteExercis(FavoriteExercise favoriteExercis) {
		getFavoriteExercises().add(favoriteExercis);
		favoriteExercis.setUser(this);

		return favoriteExercis;
	}

	public FavoriteExercise removeFavoriteExercis(FavoriteExercise favoriteExercis) {
		getFavoriteExercises().remove(favoriteExercis);
		favoriteExercis.setUser(null);

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
		plan.setUser(this);

		return plan;
	}

	public Plan removePlan(Plan plan) {
		getPlans().remove(plan);
		plan.setUser(null);

		return plan;
	}

	public List<UserEquipment> getUserEquipments() {
		return this.userEquipments;
	}

	public void setUserEquipments(List<UserEquipment> userEquipments) {
		this.userEquipments = userEquipments;
	}

	public UserEquipment addUserEquipment(UserEquipment userEquipment) {
		getUserEquipments().add(userEquipment);
		userEquipment.setUser(this);

		return userEquipment;
	}

	public UserEquipment removeUserEquipment(UserEquipment userEquipment) {
		getUserEquipments().remove(userEquipment);
		userEquipment.setUser(null);

		return userEquipment;
	}

}