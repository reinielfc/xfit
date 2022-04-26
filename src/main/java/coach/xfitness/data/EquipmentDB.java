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

    /**
    * Returns a list of all the equipment in the database
    * 
    * @return A list of all the equipment in the database.
    */
    public static List<Equipment> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Equipment.selectAll");
        List<Equipment> resultsList = DBUtil.castList(Equipment.class, query.getResultList());
        return resultsList;
    }

    /**
    * Get a list of Equipment objects from the database, where the id is in the list
    * of ids.
    * 
    * @param ids The list of ids to be used in the query.
    * @return A list of Equipment objects.
    */
    public static List<Equipment> selectByIdIn(List<Integer> ids) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        MultiIdentifierLoadAccess<Equipment> multiLoadAccess = session.byMultipleIds(Equipment.class);
        List<Equipment> equipments = multiLoadAccess.multiLoad(ids);
        return equipments;
    }

    /**
    * Select an equipment by its id.
    *
    * @param id The id of the equipment to be selected.
    * @return A single Equipment object.
    */
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

    /**
    * Get all the equipment from the database, get the name of each piece of
    * equipment, and put all the names into a list.
    * 
    * @return A list of all the names of the equipment in the database.
    */
    public static List<String> fetchNamesList() {
        return EquipmentDB.selectAll().stream()
                .map(Equipment::getName)
                .collect(Collectors.toList());
    }

}
