package coach.xfitness.data;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import coach.xfitness.business.User;

public class UserDBTest {
   
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();


    @Before
	// runs before each test starts - redirects streams to local variables
	public void setUpStreams() {
	    System.setErr(new PrintStream(err));
	}

   
    @Test
    //test that user is inputted into the data and exists in exists and deletes afterward
    public void testInsert() {
        User test = new User();
        test.setUserID((long) 1);
        test.setEmail("JHerdocia112@att.net");
        test.setName("JavierH");
        test.setPassword("pass");
        
        UserDB.insert(test);
        assertTrue(UserDB.hasUser("JHerdocia112@att.net"));
        UserDB.deleteUser(test.getEmail());
    }

    @Test
    //test that selectUser will return a null value for invalid selection
    public void testSelectInvalidUser() {
       User test = UserDB.selectUser("test@gmail.com");
       if(test != null){
           fail("Must return a null value for invalid or non exsistent user");
       }
    }

    @Test
    //test that duplicate insertion of emails will result in an error
    public void testInsertduplicateUserEmails(){
        User test1 = new User();
        test1.setUserID((long) 10);
        test1.setEmail("JSmith001@gmail.com");
        test1.setName("JohnSmith");
        test1.setPassword("pass");
        User test2 = new User();
        test2.setUserID((long) 11);
        test2.setEmail("JSmith001@gmail.com");
        test2.setName("JavierHerdocia");
        test2.setPassword("pass");

        UserDB.insert(test1);
        UserDB.insert(test2);
        UserDB.deleteUser(test1.getEmail());
        UserDB.deleteUser(test2.getEmail());
        fail("Cannot add duplicate ID's");
    }

    @Test
    //test that hasUser will return false for invalid user selection
    public void testhasUser(){
        assertFalse(UserDB.hasUser("test@gmail.com"));
    }

    @Test
    //test that User attributes cannot be null
    public void testUserNullValues(){

    }
}
