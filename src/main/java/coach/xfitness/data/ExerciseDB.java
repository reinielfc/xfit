package coach.xfitness.data;

import coach.xfitness.business.Exercise;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExerciseDB {

    // TODO: change to selectAvailable(String user) when custom exercises are integrated
    // TODO: also make a selectAvailable() that selects exercises available to logged out user
    public static List<Exercise> selectAll() {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Exercise.selectAll");
        List<Exercise> resultsList = DBUtil.castList(Exercise.class, query.getResultList());
        return resultsList;
    }

    public static Exercise select(int id) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Exercise> typedQuery = entityManager.createNamedQuery("Exercise.selectById", Exercise.class);
        typedQuery.setParameter("id", id);
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

}
