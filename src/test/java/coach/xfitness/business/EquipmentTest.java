package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class EquipmentTest {

    @Test
    //Test to see if User class exists in system
	 public void testEquipmentClassExists(){
        try{
            Class.forName("coach.xfitness.business.Equipment");
        } catch (ClassNotFoundException e){
            fail("Equipment class does not exist");
        }
    }

    @Test
    //Test to see if User constructor(s) exists
    public void testEquipmentConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.Equipment.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.Equipment()")) {
                found = true;
            }
        }
		if (!found){
			fail("Equipment class should have 1 constructor with no parameters");
        }
    }
}
