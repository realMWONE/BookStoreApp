//TODO: fix when user database is linked and initialized
package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.fakeDB.FakeUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class UserDataHandlerIntegrationTest extends TestCase {
    UserDataHandler handler;
    ArrayList<IUser> userList;
    IUser nullUser, nonExistUser, testUser, testUser_DB;
    UserDatabase db;

    public void setUp() throws Exception {
        super.setUp();
        handler = new UserDataHandler();
        nullUser = null;
        db = new UserDatabase(Main.getDBPath());
        nonExistUser = new User("Anonymous", "email@gmail.com", "software", UserType.Manager);
        testUser = new User("Matt","Matt@yahoo.com","333",UserType.Employee);

        userList = new ArrayList<>();
        userList.add(new User("Kevin","Kevin@gmail.com","1",UserType.Manager));
        userList.add(new User("Harshal","Harshall@umanitoba.ca","22",UserType.Manager));
        userList.add(testUser);
        userList.add(new User("Daniel","duy.than@gihot.com","4444",UserType.Employee));
        userList.add(new User("Animesh","Animesh@outlook.com","55555",UserType.Employee));
        userList.add(new User("Darwait","Darwait@gmail.com","666666",UserType.Employee));

        //****************** default test case: uses testUser ************************************//
        testUser_DB = db.findUser(testUser.getUserID());        //points to db copy of testUser
    }

    public void tearDown()  {
    }
    
    public void testLogIn() throws Exception {
        setUp();
        //try failure cases first
        //log in with wrong email
        handler.logIn("Matt@yahoo.com", "333");
        assertNull(UserDataHandler.currentUser);

        //wrong pw
        handler.logIn("Matt@yahoo.com", "testing");
        assertNull(UserDataHandler.currentUser);

        //correct case
        handler.logIn(testUser.getUserID(), testUser.getPassword());
        assertEquals(testUser, UserDataHandler.currentUser);
        handler.logOut();
    }

    public void testLogOut() throws Exception {
        setUp();
        handler.logIn(testUser.getUserID(), testUser.getPassword());
        if(UserDataHandler.currentUser != null) {
            handler.logOut();
            assertNull(UserDataHandler.currentUser);
        }
        else
            fail("login failed");

        //not logged in case
        try {
            handler.logOut();
            assertNull(UserDataHandler.currentUser);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testIsCurrentUserManager() throws Exception {
        setUp();
        //not manager
        handler.logIn(testUser.getUserID(), testUser.getPassword());
        assertFalse(handler.isCurrentUserManager());    //testUser is not manager

        //manager
        handler.logIn(userList.get(0).getUserID(), userList.get(0).getPassword());
        assertTrue(handler.isCurrentUserManager());    //testUser is not manager

        //not logged in
        try {
            assertFalse(handler.isCurrentUserManager());
        }catch(Exception e){
            fail("Exception "+e.getMessage()+" not caught");
        }
    }




    public void testChangePassword() throws Exception {
        setUp();
        //log in normalUser as target
        handler.logIn(testUser.getUserID(), testUser.getPassword());
        if(UserDataHandler.currentUser!=null) {
            // change password if user is logged in and all the provided information is correct
            String newPassword = "computer";

            //wrong old pw
            handler.changePassword("WRONG", newPassword, newPassword);
            assertNotEquals(newPassword, testUser_DB.getPassword());

            //new pw too short
            handler.changePassword("333", "comp", "comp");
            assertNotEquals(newPassword, testUser_DB.getPassword());

            //normal case
            handler.changePassword("333", newPassword, newPassword);
            assertEquals(newPassword, testUser_DB.getPassword());

            assertEquals(newPassword, testUser_DB.getPassword());

            //not logged in error case
            try {
                handler.changePassword("0000000", "11111111", "11111111");
            } catch (Exception e) {
                fail("Could not catch " + e.getMessage());
            }
        }else{
            fail("Log in failed");
        }
    }

    public void testAll() throws Exception {
        testChangePassword();
        testIsCurrentUserManager();
        testLogIn();
        testLogOut();
    }
}