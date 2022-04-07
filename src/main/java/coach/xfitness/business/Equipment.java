package coach.xfitness.business;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Equipment.selectAll", query = "SELECT e FROM Equipment e")
public class Equipment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @OneToMany(mappedBy = "equipmentByEquipmentId")
    private Collection<EquipmentImage> equipmentImagesById;

    @OneToMany(mappedBy = "equipmentByEquipmentId")
    private Collection<ExerciseEquipment> exerciseEquipmentsById;

    @OneToMany(mappedBy = "equipmentByEquipmentId")
    private Collection<UserEquipment> userEquipmentsById;

    // #region boilerplate

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Equipment))
            return false;
        Equipment equipment = (Equipment) o;
        return id == equipment.id
                && Objects.equals(name, equipment.name)
                && Objects.equals(equipmentImagesById, equipment.equipmentImagesById)
                && Objects.equals(exerciseEquipmentsById, equipment.exerciseEquipmentsById)
                && Objects.equals(userEquipmentsById, equipment.userEquipmentsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, equipmentImagesById, exerciseEquipmentsById, userEquipmentsById);
    }

    public Collection<EquipmentImage> getEquipmentImagesById() {
        return equipmentImagesById;
    }

    public void setEquipmentImagesById(Collection<EquipmentImage> equipmentImagesById) {
        this.equipmentImagesById = equipmentImagesById;
    }

    public Collection<ExerciseEquipment> getExerciseEquipmentsById() {
        return exerciseEquipmentsById;
    }

    public void setExerciseEquipmentsById(Collection<ExerciseEquipment> exerciseEquipmentsById) {
        this.exerciseEquipmentsById = exerciseEquipmentsById;
    }

    public Collection<UserEquipment> getUserEquipmentsById() {
        return userEquipmentsById;
    }

    public void setUserEquipmentsById(Collection<UserEquipment> userEquipmentsById) {
        this.userEquipmentsById = userEquipmentsById;
    }

    // #endregion boilerplate
}
