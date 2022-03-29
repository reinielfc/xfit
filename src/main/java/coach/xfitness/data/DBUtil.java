package coach.xfitness.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("default");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static <T> List<T> castList(Class<? extends T> _class, Collection<?> collection) {
        List<T> list = new ArrayList<>(collection.size());
        for (Object object: collection) {
            list.add(_class.cast(object));
        }
        return list;
    }

}
