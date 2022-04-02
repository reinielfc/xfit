package coach.xfitness.data;


import coach.xfitness.business.Plan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class PlanDB {
    public static void insert(Plan plan) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
                entityManager.persist(plan);
                entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("Failed to add Plan.");
        } finally {
            entityManager.close();
        }
    }

    public static Plan selectPlan(String plan) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Plan result = null;
        try{
            String queryString = "SELECT plan FROM Plan WHERE plan.planId = :planId";
            TypedQuery<Plan> typedQuery = entityManager.createQuery(queryString, Plan.class);
            typedQuery.setParameter("planId", plan);
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Could not find plan in database.");
        } finally {
            entityManager.close();
        }

        return result;
    }
    // TODO:Complete
    public static boolean hasPlan(String planName) {
        Plan plan  = selectPlan(planName);
        return plan != null;
    }  

    public static void deletePlan(String plan){
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            Plan s = selectPlan(plan);
            Plan u = entityManager.find(Plan.class, s.getPlanID());
           entityManager.remove(u);
           entityManager.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Couldn't delete Plan");
        }
        finally{
            entityManager.close();
        }
    }

    public static boolean updatePlan( Plan user) {

        return true;
    }
}
