package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class EquipmentImagePK implements Serializable {

    @Column(name = "equipmentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;

    @Column(name = "imageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    // #region boilerplate
    public EquipmentImagePK() {
    }

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
        if (this == o)
            return true;
        if (!(o instanceof EquipmentImagePK))
            return false;
        EquipmentImagePK that = (EquipmentImagePK) o;
        return equipmentId == that.equipmentId
                && imageId == that.imageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId, imageId);
    }

    // #endregion boilerplate
}
