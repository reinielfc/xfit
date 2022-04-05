package coach.xfitness.business;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "imageByImageId")
    private Collection<EquipmentImage> equipmentImagesById;
    
    @OneToMany(mappedBy = "imageByImageId")
    private Collection<ExerciseImage> exerciseImagesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (id != image1.id) return false;
        return Arrays.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public Collection<EquipmentImage> getEquipmentImagesById() {
        return equipmentImagesById;
    }

    public void setEquipmentImagesById(Collection<EquipmentImage> equipmentImagesById) {
        this.equipmentImagesById = equipmentImagesById;
    }

    public Collection<ExerciseImage> getExerciseImagesById() {
        return exerciseImagesById;
    }

    public void setExerciseImagesById(Collection<ExerciseImage> exerciseImagesById) {
        this.exerciseImagesById = exerciseImagesById;
    }
}
