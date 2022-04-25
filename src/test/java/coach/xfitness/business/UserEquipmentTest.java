package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class UserEquipmentTest {
    
    @Test
    //Test to see if User class exists in system
	 public void testUserEquipmentClassExists(){
        try{
            Class.forName("coach.xfitness.business.UserEquipment");
        } catch (ClassNotFoundException e){
            fail("UserEquipment class does not exist");
        }
    }

    @Test
    //Test to see if User constructor(s) exists
    public void testUserConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.UserEquipment.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.UserEquipment()")) {
                found = true;
            }
        }
		if (!found){
			fail("UserEquipment class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetEquipmentByEquipmentID() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserEquipment.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserEquipment.getEquipmentByEquipmentId()")) {
                Equipment test = new Equipment();
                test.setEquipmentByEquipmentId();
                if (test.getEquipmentByEquipmentID().equals()) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("UserEquipment class should have a public method called getEquipmentByEquipmentId");
        }
    }

}
