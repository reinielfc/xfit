package coach.xfitness.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import coach.xfitness.business.Muscle;

public class MuscleDB {

    /**
    * Returns a list of all the muscles in the database
    * 
    * @return A list of all the muscles in the database.
    */
    public static List<Muscle> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Muscle.selectAll");
        return DBUtil.castList(Muscle.class, query.getResultList());
    }

    /**
    * Returns a list of all the names of the muscles in the database.
    * 
    * @return A list of all the names of the muscles in the database.
    */
    public static List<String> fetchNamesList() {
        return selectAll().stream()
            .map(Muscle::getName)
            .collect(Collectors.toList());
    }

}
