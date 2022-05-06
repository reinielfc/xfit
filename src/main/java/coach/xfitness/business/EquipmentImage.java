package coach.xfitness.business;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
public class EquipmentImage {
    @EmbeddedId
    private EquipmentImagePK id;

    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId", referencedColumnName = "id", nullable = false)
    private Equipment equipmentByEquipmentId;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "imageId", referencedColumnName = "id", nullable = false)
    private Image imageByImageId;

    // #region boilerplate
    public EquipmentImage() {
    }

    public EquipmentImagePK getId() {
        return id;
    }

    public void setId(EquipmentImagePK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EquipmentImage))
            return false;
        EquipmentImage that = (EquipmentImage) o;
        return Objects.equals(id, that.id)
                && Objects.equals(equipmentByEquipmentId, that.equipmentByEquipmentId)
                && Objects.equals(imageByImageId, that.imageByImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipmentByEquipmentId, imageByImageId);
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

    // #endregion boilerplate
}
