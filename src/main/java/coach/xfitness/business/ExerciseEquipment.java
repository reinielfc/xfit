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
@IdClass(ExerciseEquipmentPK.class)
public class ExerciseEquipment {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exerciseId", nullable = false)
    private int exerciseId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "equipmentId", nullable = false)
    private int equipmentId;

    @ManyToOne
    @JoinColumn(name = "exerciseId", referencedColumnName = "id", nullable = false)
    private Exercise exerciseByExerciseId;

    @ManyToOne
    @JoinColumn(name = "equipmentId", referencedColumnName = "id", nullable = false)
    private Equipment equipmentByEquipmentId;

    // #region boilerplate

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
        if (o == null || getClass() != o.getClass())
            return false;

        ExerciseEquipment that = (ExerciseEquipment) o;

        if (exerciseId != that.exerciseId)
            return false;
        return equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        int result = exerciseId;
        result = 31 * result + equipmentId;
        return result;
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
