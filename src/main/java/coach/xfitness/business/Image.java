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
@NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Lob
	private byte[] image;

	@OneToMany(mappedBy = "image")
	private List<ExerciseImage> exerciseImages;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<ExerciseImage> getExerciseImages() {
		return this.exerciseImages;
	}

	public void setExerciseImages(List<ExerciseImage> exerciseImages) {
		this.exerciseImages = exerciseImages;
	}

	public ExerciseImage addExerciseImage(ExerciseImage exerciseImage) {
		getExerciseImages().add(exerciseImage);
		exerciseImage.setImage(this);

		return exerciseImage;
	}

	public ExerciseImage removeExerciseImage(ExerciseImage exerciseImage) {
		getExerciseImages().remove(exerciseImage);
		exerciseImage.setImage(null);

		return exerciseImage;
	}

}