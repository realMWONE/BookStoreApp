package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerTest;

public class AllTests {
    //For the class LoggedInUserTest
    public static void main(String[] args) throws ClassNotFoundException {
        BookDataHandlerTest bookHandlerTest = new BookDataHandlerTest();
//
//        BookDatabaseTest.testSetBookList();
//        BookDatabaseTest.testFindBook();
//
//        BookTest.testGetBookName();
//        BookTest.testGetBookAuthor();
//        BookTest.testGetPrice();
//        BookTest.testGetBookIsbn();
//        BookTest.testGetStockAmount();
//        BookTest.testSetPrice();
//        BookTest.testSetStockAmount();
//
//        UserTest.testGetRealName();
//        UserTest.testGetUserType();
//        UserTest.testGetUserID();
//        UserTest.testGetPassword();
//        UserTest.testSetUserID();
//        UserTest.testSetPassword();

        bookHandlerTest.testSetPrice();
        bookHandlerTest.testIncrementPrice();
        bookHandlerTest.testDecrementPrice();
        bookHandlerTest.testSetStock();
        bookHandlerTest.testIncrementStock();
        bookHandlerTest.testDecrementStock();
    }

}
