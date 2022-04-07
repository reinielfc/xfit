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

    public static void insert(Exercise exercise) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            entityManager.persist(exercise);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            System.out.println("Failed to add Exercise.");
        } finally {
            entityManager.close();
        }
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

    public static boolean hasExercise(String exercise) {
        boolean success = false;
        Exercise myExercise = selectExercise(exercise);
        if(myExercise != null) 
        success = true;

        return success;
    }  

    public static void deleteExercise(String exercise){
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            Exercise s = selectExercise(exercise);
            Exercise u = entityManager.find(Exercise.class, s.getExerciseID());
           entityManager.remove(u);
           entityManager.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Couldn't delete Exercise");
        }
        finally{
            entityManager.close();
        }
    }

    public static boolean updateExercise( Exercise exercise) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
                entityManager.merge(exercise);
                entityTransaction.commit();

        } catch (Exception e) {
            System.out.println("Failed to update Exercise.");
        } finally {
            entityManager.close();
        }
        return true; // does this need to return true

    }

    
}// 
