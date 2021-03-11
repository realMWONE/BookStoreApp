package com.comp3350_group10.bookstore.data.model;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.User;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandler;

import junit.framework.TestCase;

import org.junit.Before;

public class UserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public static void testGetRealName() {
        //In this function, I default these fields:
        //userId= 1, password = 2, UserType=Employee
        String[] names = new String[]{"Dan","Matt","Harshal"};
        for(int i=0;i<names.length;i++){
            User u = new User(names[i],"1","2", UserType.Employee);
            assertEquals(u.getRealName(),names[i]);
        }
    }

    public static void testGetUserType() {
        //In this function, I default these fields:
        //userName = Dan, userId= 1, password = 2
        User u = new User("Dan","1","2", UserType.Employee);
        assertEquals(u.getUserType(),UserType.Employee);
        User u1 = new User("Kevin","2","3", UserType.Manager);
        assertEquals(u1.getUserType(),UserType.Manager);
    }

    public static void testGetUserID() {
        //In this function, I default these fields:
        //userName = Dan, Mat ; , password = 2,
        String[] userIds = new String[]{"1","2","3"};
        for(int i=0;i<userIds.length;i++){
            User u = new User("Dan",userIds[i],"2", UserType.Employee);
            assertEquals(u.getUserID(),userIds[i]);
            User u1 = new User("Mat",userIds[i],"3", UserType.Manager);
            assertEquals(u1.getUserID(),userIds[i]);
        }
    }

    public static void testGetPassword() {
        //In this function, I default these fields:
        //userName = Dan, Mat ; userId = 1,2
        String[] passwords = new String[]{"123a","456b","abc"};
        for(int i=0;i<passwords.length;i++){
            User u = new User("Dan","1",passwords[i], UserType.Employee);
            assertEquals(u.getPassword(),passwords[i]);
            User u1 = new User("Mat","2",passwords[i], UserType.Manager);
            assertEquals(u1.getPassword(),passwords[i]);
        }
    }

    public static void testSetUserID() {
        //only change userId, other fields don't matter
        String[] userIds = new String[]{"1","2","3"};
        User u1 = new User("Dan","a","123456",UserType.Employee);
        User u2 = new User("Harshal","b","57691",UserType.Manager);
        User u3 = new User("Animesh","c","32123a",UserType.Manager);

        u1.setUserID(userIds[0]);
        u2.setUserID(userIds[1]);
        u3.setUserID(userIds[2]);

        assertEquals(u1.getUserID(),userIds[0]);
        assertEquals(u2.getUserID(),userIds[1]);
        assertEquals(u3.getUserID(),userIds[2]);
    }

    public static void testSetPassword() {
        //only change userPassword, other fields don't matter
        String[] userPasswords = new String[]{"1234","abcd","1a2b3c"};
        User u1 = new User("Dan","1","1a",UserType.Employee);
        User u2 = new User("Harshal","2","2b",UserType.Manager);
        User u3 = new User("Animesh","3","3c",UserType.Manager);
        u1.setPassword(userPasswords[0]);
        u2.setPassword(userPasswords[1]);
        u3.setPassword(userPasswords[2]);
        assertEquals(u1.getPassword(),userPasswords[0]);
        assertEquals(u2.getPassword(),userPasswords[1]);
        assertEquals(u3.getPassword(),userPasswords[2]);
    }

    public static void testChangePassword(){
        //change password if user is logged in and all the provided information is correct
        User u1 = new User("Anonymous","1","software",UserType.Manager);
        User u2 = null;
        String newPassword= "computer";
        DataHandler dataHandler = new DataHandler(u1);
        DataHandler dataHandler1 = new DataHandler(u2);

        dataHandler1.changePassword("software",newPassword,"computer");
        dataHandler.changePassword("SOFTWARE",newPassword,"computer");
        dataHandler.changePassword("software","comp","comp");
        dataHandler.changePassword("software",newPassword,"Computer");
        dataHandler.changePassword("software",newPassword,"computer");

        assertEquals(newPassword,u1.getPassword());


    }

}