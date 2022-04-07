package coach.xfitness.business;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ExerciseEquipmentPK implements Serializable {

    @Column(name = "exerciseId", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;

    @Column(name = "equipmentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;

    // #region boilerplate
    public ExerciseEquipmentPK() {
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
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
        if (!(o instanceof ExerciseEquipmentPK))
            return false;
        ExerciseEquipmentPK that = (ExerciseEquipmentPK) o;
        return exerciseId == that.exerciseId
                && equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, equipmentId);
    }

    // #endregion boilerplate
}
