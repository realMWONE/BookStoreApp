package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerTest;
import com.comp3350_group10.bookstore.business.Data_Handler.UserDataHandlerTest;

public class AllTests {
    public static void main(String[] args) throws ClassNotFoundException {
        BookDataHandlerTest bookHandlerTest = new BookDataHandlerTest();
        UserDataHandlerTest userHandlerTest = new UserDataHandlerTest();

        userHandlerTest.testChangePassword();
        userHandlerTest.testIsCurrentUserManager();
        userHandlerTest.testLogIn();
        userHandlerTest.testLogOut();

        bookHandlerTest.testSetPrice();
        bookHandlerTest.testIncrementPrice();
        bookHandlerTest.testDecrementPrice();
        bookHandlerTest.testSetStock();
        bookHandlerTest.testIncrementStock();
        bookHandlerTest.testDecrementStock();
    }

}
