package coach.xfitness.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;

import coach.xfitness.business.Equipment;

public class EquipmentDB {

    public static List<Equipment> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Equipment.selectAll");
        List<Equipment> resultsList = DBUtil.castList(Equipment.class, query.getResultList());
        return resultsList;
    }

    public static List<Equipment> selectByIdIn(List<Integer> ids) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        MultiIdentifierLoadAccess<Equipment> multiLoadAccess = session.byMultipleIds(Equipment.class);
        List<Equipment> equipments = multiLoadAccess.multiLoad(ids);
        return equipments;
    }

    public static Equipment selectById(int id) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Equipment> typedQuery = entityManager.createNamedQuery("Equipment.selectById", Equipment.class);
        typedQuery.setParameter("id", id);
        Equipment result = null;

        try {
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<String> fetchNamesList() {
        return EquipmentDB.selectAll().stream()
                .map(Equipment::getName)
                .collect(Collectors.toList());
    }

}
