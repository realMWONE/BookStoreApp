package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.data.BookDatabaseTest;
import com.comp3350_group10.bookstore.data.BookTest;
import com.comp3350_group10.bookstore.data.UserTest;
import com.comp3350_group10.bookstore.data.model.LoggedInUserTest;
import com.comp3350_group10.bookstore.logic.Data_Handler.DataHandlerTest;

public class AllTests {
    //For the class LoggedInUserTest
    public static void main(String[] args){
        //for LoggedInUserTest
        LoggedInUserTest.testGetUserId();
        LoggedInUserTest.testGetDisplayName();
        //for BookDatabaseTest
        BookDatabaseTest.testSetBookList();
        BookDatabaseTest.testFindBook();
        //for BookTest
        BookTest.testGetBookName();
        BookTest.testGetBookAuthor();
        BookTest.testGetPrice();
        BookTest.testGetBookIsbn();
        BookTest.testGetStockAmount();
        BookTest.testSetPrice();
        BookTest.testSetStockAmount();
        //for LoginDataSourceTest
        //for LoginReposityTest
        //for UserTest
        UserTest.testGetRealName();
        UserTest.testGetUserType();
        UserTest.testGetUserID();
        UserTest.testGetPassword();
        UserTest.testSetUserID();
        UserTest.testSetPassword();
        //for DataHandlerTest
        DataHandlerTest.testSetPrice();
        DataHandlerTest.testIncrementPrice();
        DataHandlerTest.testDecrementPrice();
        DataHandlerTest.testSetStock();
        DataHandlerTest.testIncrementStock();
        DataHandlerTest.testDecrementStock();
        DataHandlerTest.testIsCurrentUserManager();
        DataHandlerTest.testIsCurrentUserManager();
    }

}
