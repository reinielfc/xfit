package coach.xfitness.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import coach.xfitness.business.Equipment;

public class EquipmentDB {

    public static List<Equipment> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Equipment.selectAll");
        List<Equipment> resultsList = DBUtil.castList(Equipment.class, query.getResultList());
        return resultsList;
    }

    public static List<String> fetchNamesList() {
        return EquipmentDB.selectAll().stream()
            .map(Equipment::getName)
            .collect(Collectors.toList());
    }

}
