package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class UserEquipmentPK implements Serializable {

    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "equipmentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;

    public UserEquipmentPK(int userId, int equipmentId) {
        this.userId = userId;
        this.equipmentId = equipmentId;
    }

    // #region boilerplate
    public UserEquipmentPK() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserEquipmentPK))
            return false;
        UserEquipmentPK that = (UserEquipmentPK) o;
        return userId == that.userId
                && equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, equipmentId);
    }

    // #endregion boilerplate
}
