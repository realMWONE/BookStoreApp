package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerTest;
import com.comp3350_group10.bookstore.business.Data_Handler.UserDataHandlerTest;
import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;

public class AllTests {
    public static void main(String[] args) throws Exception {
        //TODO: code coverage lower than running tests individually...?
        BookDataHandlerTest bookHandlerTest = new BookDataHandlerTest();
        UserDataHandlerTest userHandlerTest = new UserDataHandlerTest();
        BookTest bookTest = new BookTest();
        UserTest userTest = new UserTest();

        userHandlerTest.testChangePassword();
        userHandlerTest.testIsCurrentUserManager();
        userHandlerTest.testLogIn();
        userHandlerTest.testLogOut();

        bookHandlerTest.setUp();
        bookHandlerTest.testSetPrice();
        bookHandlerTest.testIncrementPrice();
        bookHandlerTest.testDecrementPrice();
        bookHandlerTest.testSetStock();
        bookHandlerTest.testIncrementStock();
        bookHandlerTest.testDecrementStock();

        bookTest.testGetBookAuthor();
        bookTest.testGetBookIsbn();
        bookTest.testGetBookName();
        bookTest.testGetDate();
        bookTest.testGetGenre();
        bookTest.testGetImage();
        bookTest.testGetPrice();
        bookTest.testGetReserve();
        bookTest.testGetStockAmount();
        bookTest.testSetPrice();
        bookTest.testSetReserve();
        bookTest.testSetStockAmount();

        userTest.testGetPassword();
        userTest.testGetRealName();
        userTest.testGetUserID();
        userTest.testGetUserType();
        userTest.testSetPassword();
        userTest.testSetUserID();
    }


}
