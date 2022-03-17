package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class UserTest {

	@Test
    //Test to see if User class exists in system
	 public void testUserClassExists(){
        try{
            Class.forName("coach.xfitness.business.User");
        } catch (ClassNotFoundException e){
            fail("User class does not exist");
        }
    }

    @Test
    //Test to see if User constructor(s) exists
    public void testUserConstructorsExist(){
        boolean found = false;  
		Constructor list[] = coach.xfitness.business.User.class.getConstructors();  // get all constructors
		for(int i = 0; i < list.length; i++)  // loop through list of Constructors
		{
            String value = ""+list[i]; // convert to string
			if (value.contentEquals("public coach.xfitness.business.User()")){
				found = true;
            }
		}
		if (!found){
			fail("User class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetEmail() {
        boolean found = false;  
		Method list[] = coach.xfitness.business.User.class.getDeclaredMethods();
		for(int i = 0; i < list.length; i++) // loop through list of attributes
		{
			String value = ""+list[i]; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.User.getEmail()")){
				User test = new User();
                test.setEmail("JSmith001@gmail.com");
                if(test.getEmail().equals("JSmith001@gmail.com")){
                    found = true;
                }
            }   
		}
		if (!found){
			fail("User class should have a public method called getEmail");
        }
    }

    @Test
    //test if method exists and  returns a name attribute as a string
    public void testGetName() {
        boolean found = false;  
		Method list[] = coach.xfitness.business.User.class.getDeclaredMethods();
		for(int i = 0; i < list.length; i++) // loop through list of attributes
		{
			String value = ""+list[i]; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.User.getName()")){
                User test = new User();
                test.setName("John");
                if(test.getName().equals("John")){
                    found = true;
                }
            }
		}
		if (!found){
			fail("User class should have a public method called getName");
        }
    }
    
    @Test
    //test if method exists and returns a password attribute as a string
    public void testGetPassword() {
        boolean found = false;  
		Method list[] = coach.xfitness.business.User.class.getDeclaredMethods();
		for(int i = 0; i < list.length; i++) // loop through list of attributes
		{
			String value = ""+list[i]; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.User.getPassword()")){
				User test = new User();
                test.setPassword("Pass");  
                if(test.getPassword().equals("Pass")){
                    found = true;
                }
            }
        }
		if (!found){
			fail("User class should have a public method called getPassword");
        }
    }

    @Test
    //test if method exists and returns a UserID attribute as a long
    public void testGetUserID() {
        boolean found = false;  
		Method list[] = coach.xfitness.business.User.class.getDeclaredMethods();
		for(int i = 0; i < list.length; i++) // loop through list of attributes
		{
			String value = ""+list[i]; // convert to string
            if (value.contentEquals("public java.lang.Long coach.xfitness.business.User.getUserID()")){
				User test = new User();
                test.setUserID((long) 300);
                if(test.getUserID() == 300){
                    found = true;
                }
            }
		}
		if (!found){
			fail("User class should have a public method called getUserID");
        }
    }
}
