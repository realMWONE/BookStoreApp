package com.comp3350_group10.bookstore.business.Data_Handler;

import junit.framework.TestCase;

public class UserDataHandlerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testIsCurrentUserManager() {
    }

    public void testLogIn() {
    }

    public void testLogOut() {
    }

    public void testChangePassword() {
        change password if user is logged in and all the provided information is correct
        User u1 = new User("Anonymous","1","software",UserType.Manager);
        User u2 = null;
        String newPassword= "computer";
        BookDataHandler dataHandler = new BookDataHandler(u1);
        BookDataHandler dataHandler1 = new BookDataHandler(u2);

        dataHandler1.changePassword("software",newPassword,"computer");
        dataHandler.changePassword("SOFTWARE",newPassword,"computer");
        dataHandler.changePassword("software","comp","comp");
        dataHandler.changePassword("software",newPassword,"Computer");
        dataHandler.changePassword("software",newPassword,"computer");

        assertEquals(newPassword,u1.getPassword());
    }

    //    public static void testChangePassword(){
//        //change password if user is logged in and all the provided information is correct
//        User u1 = new User("Anonymous","1","software",UserType.Manager);
//        User u2 = null;
//        String newPassword= "computer";
//        BookDataHandler dataHandler = new BookDataHandler(u1);
//        BookDataHandler dataHandler1 = new BookDataHandler(u2);
//
//        dataHandler1.changePassword("software",newPassword,"computer");
//        dataHandler.changePassword("SOFTWARE",newPassword,"computer");
//        dataHandler.changePassword("software","comp","comp");
//        dataHandler.changePassword("software",newPassword,"Computer");
//        dataHandler.changePassword("software",newPassword,"computer");
//
//        assertEquals(newPassword,u1.getPassword());
//
//
//    }
//
//    //CAN'T TEST THIS SINCE THE USER IS PRIVATE AND NO ACCESSIBILITY TO IT
//    public static void testIsCurrentUserManager() {
//
//    }
//    //THIS AS WELL
//    public static void testLogOut() {
//    }
}