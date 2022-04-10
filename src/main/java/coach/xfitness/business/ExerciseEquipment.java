package coach.xfitness.business;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Entity
public class ExerciseEquipment {

    @EmbeddedId
    private ExerciseEquipmentPK id;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId", referencedColumnName = "id", nullable = false)
    private Equipment equipmentByEquipmentId;

    // #region boilerplate
    public ExerciseEquipment() {
    }

    public ExerciseEquipmentPK getId() {
        return id;
    }

    public void setId(ExerciseEquipmentPK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ExerciseEquipment))
            return false;
        ExerciseEquipment that = (ExerciseEquipment) o;
        return Objects.equals(id, that.id)
                && Objects.equals(exerciseByExerciseId, that.exerciseByExerciseId)
                && Objects.equals(equipmentByEquipmentId, that.equipmentByEquipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseByExerciseId, equipmentByEquipmentId);
    }

    public Exercise getExerciseByExerciseId() {
        return exerciseByExerciseId;
    }

    public void setExerciseByExerciseId(Exercise exerciseByExerciseId) {
        this.exerciseByExerciseId = exerciseByExerciseId;
    }

    public Equipment getEquipmentByEquipmentId() {
        return equipmentByEquipmentId;
    }

    public void setEquipmentByEquipmentId(Equipment equipmentByEquipmentId) {
        this.equipmentByEquipmentId = equipmentByEquipmentId;
    }

    // #endregion boilerplate
}
