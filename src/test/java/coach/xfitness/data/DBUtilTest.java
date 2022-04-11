package coach.xfitness.data;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import coach.xfitness.business.User;
import coach.xfitness.data.DBUtil;

public class DBUtilTest {

    private static Collection<?> collection1;
    private static Collection<?> collection2;

    @Test
    public void <T> List<T> castListTest(){
        List<T> list1 = new ArrayList<>(collection1.size());
        List<T> list2 = new ArrayList<>(collection2.size());
        Class<? extends T> _class;
        boolean actual = false;

        DBUtil.castList(_class, collection1);
        DBUtil.castList(_class, collection2);

        assertArrayEquals(list1.toArray(), list2.toArray());



    }

}
