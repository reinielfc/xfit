package coach.xfitness.data;

import coach.xfitness.business.Equipment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EquipmentDB {

    public static List<Equipment> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Equipment.selectAll");
        List<Equipment> resultsList = DBUtil.castList(Equipment.class, query.getResultList());
        entityManager.close();
        return resultsList;
    }

}
