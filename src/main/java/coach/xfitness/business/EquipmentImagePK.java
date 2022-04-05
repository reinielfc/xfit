package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EquipmentImagePK implements Serializable {
    
    @Column(name = "equipmentId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;
    
    @Column(name = "imageId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

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

        EquipmentImagePK that = (EquipmentImagePK) o;

        if (equipmentId != that.equipmentId) return false;
        return imageId == that.imageId;
    }

    @Override
    public int hashCode() {
        int result = equipmentId;
        result = 31 * result + imageId;
        return result;
    }
}
