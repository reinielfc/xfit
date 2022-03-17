package coach.xfitness.data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("XFitPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
