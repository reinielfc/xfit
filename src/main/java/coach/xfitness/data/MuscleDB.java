package coach.xfitness.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import coach.xfitness.business.Muscle;

public class MuscleDB {

    public static List<Muscle> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Muscle.selectAll");
        List<Muscle> resultsList = DBUtil.castList(Muscle.class, query.getResultList());
        return resultsList;
    }

    public static List<String> fetchNamesList() {
        return MuscleDB.selectAll().stream()
            .map(Muscle::getName)
            .collect(Collectors.toList());
    }

}