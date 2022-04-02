package coach.xfitness.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coach.xfitness.business.Exercise;

public class ExerciseDB {

    // TODO: change to selectAvailable(String user) when custom exercises are
    // integrated
    // TODO: also make a selectAvailable() that selects exercises available to
    // logged out user
    public static List<Exercise> selectAll() {
        EntityManager entityManager = null;
        List<Exercise> resultsList = null;
        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectAll");
            resultsList = DBUtil.castList(Exercise.class, query.getResultList());
        } catch (NoResultException e) {
            System.err.println(e);
        }
        return resultsList;
    }

    public static Exercise select(String name) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Exercise> typedQuery = entityManager.createNamedQuery("Exercise.selectByName", Exercise.class);
        typedQuery.setParameter("name", name);
        Exercise result = null;

        try {
            result = typedQuery.getSingleResult();
            System.out.println();
        } catch (NoResultException e) {
            System.err.println("Exercise Not Found");
            System.err.println(e);
        }

        return result;
    }

    public static List<String> fetchTypesList() {
        EntityManager entityManager = null;
        List<String> resultsList = null;
        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectDistinctTypes");
            resultsList = DBUtil.castList(String.class, query.getResultList());
        } catch (NoResultException e) {
            System.err.println(e);
        }
        return resultsList;
    }

}
