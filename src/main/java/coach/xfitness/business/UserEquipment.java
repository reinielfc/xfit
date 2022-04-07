package coach.xfitness.business;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserEquipment {

    @EmbeddedId
    private UserEquipmentPK id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId", referencedColumnName = "id", nullable = false)
    private Equipment equipmentByEquipmentId;

    public UserEquipment(User userByUserId, Equipment equipmentByEquipmentId) {
        this.id = new UserEquipmentPK(userByUserId.getId(), equipmentByEquipmentId.getId());
        this.userByUserId = userByUserId;
        this.equipmentByEquipmentId = equipmentByEquipmentId;
    }

    // #region boilerplate
    public UserEquipment() {
    }

    public UserEquipmentPK getId() {
        return id;
    }

    public void setId(UserEquipmentPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserEquipment))
            return false;
        UserEquipment that = (UserEquipment) o;
        return Objects.equals(id, that.id)
                && Objects.equals(userByUserId, that.userByUserId)
                && Objects.equals(equipmentByEquipmentId, that.equipmentByEquipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userByUserId, equipmentByEquipmentId);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Equipment getEquipmentByEquipmentId() {
        return equipmentByEquipmentId;
    }

    public void setEquipmentByEquipmentId(Equipment equipmentByEquipmentId) {
        this.equipmentByEquipmentId = equipmentByEquipmentId;
    }

    // #endregion boilerplate
}
