package coach.xfitness.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(EquipmentImagePK.class)
public class EquipmentImage {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "equipmentId", nullable = false)
    private int equipmentId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "imageId", nullable = false)
    private int imageId;

    @ManyToOne
    @JoinColumn(name = "equipmentId", referencedColumnName = "id", nullable = false)
    private Equipment equipmentByEquipmentId;
    
    @ManyToOne
    @JoinColumn(name = "imageId", referencedColumnName = "id", nullable = false)
    private Image imageByImageId;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentImage that = (EquipmentImage) o;

        if (equipmentId != that.equipmentId) return false;
        return imageId == that.imageId;
    }

    @Override
    public int hashCode() {
        int result = equipmentId;
        result = 31 * result + imageId;
        return result;
    }

    public Equipment getEquipmentByEquipmentId() {
        return equipmentByEquipmentId;
    }

    public void setEquipmentByEquipmentId(Equipment equipmentByEquipmentId) {
        this.equipmentByEquipmentId = equipmentByEquipmentId;
    }

    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }
}
