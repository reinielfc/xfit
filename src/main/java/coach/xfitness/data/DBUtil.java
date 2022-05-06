package coach.xfitness.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    /**
    * If the entity manager factory is null, create it, otherwise return it.
    * 
    * @return The entityManagerFactory object.
    */
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
    * It takes a collection of objects and returns a list of objects of the same
    * type.
    * 
    * @param _class The class to cast to.
    * @param collection The collection to be cast.
    * @return A list of the same type as the class passed in.
    */
    public static <T> List<T> castList(Class<? extends T> _class, Collection<?> collection) {
        List<T> list = new ArrayList<>(collection.size());
        for (Object object: collection) {
            list.add(_class.cast(object));
        }
        return list;
    }

}
