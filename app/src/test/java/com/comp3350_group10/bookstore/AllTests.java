package com.comp3350_group10.bookstore;

import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerIntegrationTest;
import com.comp3350_group10.bookstore.business.Data_Handler.BookDataHandlerUnitTest;
import com.comp3350_group10.bookstore.business.Data_Handler.UserDataHandlerUnitTest;
import com.comp3350_group10.bookstore.objects.BookTest;
import com.comp3350_group10.bookstore.objects.UserTest;

public class AllTests {
    public static void main(String[] args) throws Exception {
        BookDataHandlerUnitTest bookHandlerTest = new BookDataHandlerUnitTest();
        UserDataHandlerUnitTest userHandlerTest = new UserDataHandlerUnitTest();
        BookTest bookTest = new BookTest();
        UserTest userTest = new UserTest();
        //BookDataHandlerIntegrationTest bookHandler_ITest = new BookDataHandlerIntegrationTest();  //TODO: uncomment when database works

        userHandlerTest.testAll();

        bookHandlerTest.testAll();

        bookTest.testAll();

        userTest.testAll();

//        bookHandler_ITest.testAll();
    }


}
