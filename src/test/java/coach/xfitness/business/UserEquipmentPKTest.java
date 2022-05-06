package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class UserEquipmentPKTest {
    
    @Test
    //Test to see if UserEquipmentPK class exists in system
	 public void testUserEquipmentPKClassExists(){
        try{
            Class.forName("coach.xfitness.business.UserEquipmentPK");
        } catch (ClassNotFoundException e){
            fail("UserEquipmentPK class does not exist");
        }
    }

    @Test
    //Test to see if UserEquipmentPK constructor(s) exists
    public void testUserEquipmentPKConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.UserEquipmentPK.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.UserEquipmentPK()")) {
                found = true;
            }
        }
		if (!found){
			fail("UserEquipmentPK class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetUserId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserEquipmentPK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserEquipmentPK.getUserId()")) {
                UserEquipmentPK test = new UserEquipmentPK();
                test.setUserId(300);
                if (test.getUserId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("UserEquipmentPK class should have a public method called getUserId");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetEquipmentId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserEquipmentPK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserEquipmentPK.getEquipmentId()")) {
                UserEquipmentPK test = new UserEquipmentPK();
                test.setEquipmentId(300);
                if (test.getEquipmentId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("UserEquipmentPK class should have a public method called getEquipmentId");
        }
    }
}
