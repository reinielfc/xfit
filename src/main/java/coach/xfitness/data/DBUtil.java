package coach.xfitness.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("XFitPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
