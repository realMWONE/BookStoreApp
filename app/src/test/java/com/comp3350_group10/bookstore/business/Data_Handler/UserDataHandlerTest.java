package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.IUser;
import com.comp3350_group10.bookstore.persistence.UserType;
import com.comp3350_group10.bookstore.persistence.fakeDB.FakeUserDatabase;

import junit.framework.TestCase;

public class UserDataHandlerTest extends TestCase {
    UserDataHandler handler;
    IUser normalUser, nullUser, matt, karen;
    FakeUserDatabase userDB;

    public void setUp() throws Exception {
        super.setUp();
        nullUser = null;

        normalUser = new User("Anonymous", "email@gmail.com", "software", UserType.Manager);
        matt = new User("Matt", "sthwrong@myumanitoba.ca", "AYYlmao420", UserType.Employee);
        karen = new User("Karen", "whereismanager@gmail.com", "12345678", UserType.Manager);

        userDB = new FakeUserDatabase();

        userDB.createUser(normalUser);
        userDB.createUser(matt);
        userDB.createUser(karen);

        //handler will be initialized later as tests need different cases
    }

    public void tearDown() throws Exception {
    }

    public void testIsCurrentUserManager() throws Exception {
        setUp();
        //manager
        handler = new UserDataHandler(userDB, karen);
        assertTrue(handler.isCurrentUserManager());

        //not manager
        handler = new UserDataHandler(userDB, matt);
        assertFalse(handler.isCurrentUserManager());

        //null user
        try {
            handler = new UserDataHandler(userDB, null);
        } catch (NullPointerException e) {
            fail("NullPointerException not caught");
        }
        assertFalse(handler.isCurrentUserManager());
    }

    public void testLogIn() throws Exception {
        setUp();
        handler = new UserDataHandler(userDB, null);

        //try failure cases first
        //log in with wrong email
        handler.logIn("wong@wong.wong", "AYYlmao420");
        assertNull(UserDataHandler.currentUser);

        //wrong pw
        handler.logIn("sthwrong@myumanitoba.ca", "testing");
        assertNull(UserDataHandler.currentUser);

        //correct case
        handler.logIn(matt.getUserID(), matt.getPassword());
        assertEquals(matt, UserDataHandler.currentUser);
    }

    public void testLogOut() throws Exception {
        setUp();
        //log in Matt as normal case
        handler = new UserDataHandler(userDB, matt);
        handler.logOut();
        assertNull(UserDataHandler.currentUser);

        //log in null to see how it works
        handler = new UserDataHandler(userDB, nullUser);
        handler.logOut();
        assertNull(UserDataHandler.currentUser);
    }

    public void testChangePassword() throws Exception {
        setUp();
        //log in normalUser as target
        handler = new UserDataHandler(userDB, normalUser);

        // change password if user is logged in and all the provided information is correct
        String newPassword = "computer";

        //wrong old pw
        handler.changePassword("SOFTWARE", newPassword, "computer");
        assertFalse(newPassword.equals(normalUser.getPassword()));

        //new pw too short
        handler.changePassword("software", "comp", "comp");
        assertFalse(newPassword.equals(normalUser.getPassword()));

        //normal case
        handler.changePassword("software", newPassword, "computer");
        assertTrue(newPassword.equals(normalUser.getPassword()));

        assertEquals(newPassword, normalUser.getPassword());

        //not logged in error case
        handler = new UserDataHandler(userDB, null);
        try {
            handler.changePassword("0000000", "11111111", "11111111");
        } catch (Exception e) {
            fail("Could not catch " + e.toString());
        }

    }
}