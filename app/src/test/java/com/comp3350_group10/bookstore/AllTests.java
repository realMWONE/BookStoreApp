package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerTest;
import com.comp3350_group10.bookstore.business.Data_Handler.UserDataHandlerTest;
import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;

public class AllTests {
    public static void main(String[] args) throws Exception {
        BookDataHandlerTest bookHandlerTest = new BookDataHandlerTest();
        UserDataHandlerTest userHandlerTest = new UserDataHandlerTest();
        BookTest bookTest = new BookTest();
        UserTest userTest = new UserTest();

        userHandlerTest.testAll();

        bookHandlerTest.testAll();

        bookTest.testAll();

        userTest.testAll();
    }


}
