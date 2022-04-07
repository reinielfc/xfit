package coach.xfitness.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import coach.xfitness.business.Exercise;

public class ExerciseDB {

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

    public static Exercise selectExercise(String exercise) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Exercise result = null;
        try{
            String queryString = "SELECT exercise FROM User user WHERE user.exercise = :exercise";
            TypedQuery<Exercise> typedQuery = entityManager.createQuery(queryString, Exercise.class);
            typedQuery.setParameter("exercise", exercise);
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Could not find exercise in database.");
        } finally {
            entityManager.close();
        }

        return result;
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
