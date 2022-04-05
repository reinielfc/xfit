package coach.xfitness.business;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ExerciseEquipmentPK implements Serializable {
    @Column(name = "exerciseId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseId;
    
    @Column(name = "equipmentId", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExerciseEquipmentPK that = (ExerciseEquipmentPK) o;

        if (exerciseId != that.exerciseId) return false;
        return equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + equipmentId;
        return result;
    }
}
