package coach.xfitness.business;

import java.io.Serializable;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p")
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	private int dayOfWeek;

	private Time duration;

	private short reps;

	private short sets;

	private short weight;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "exerciseID")
	private Exercise exercise;

	public Plan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Time getDuration() {
		return this.duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public short getReps() {
		return this.reps;
	}

	public void setReps(short reps) {
		this.reps = reps;
	}

	public short getSets() {
		return this.sets;
	}

	public void setSets(short sets) {
		this.sets = sets;
	}

	public short getWeight() {
		return this.weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exercise getExercise() {
		return this.exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

}