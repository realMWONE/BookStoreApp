package com.comp3350_group10.bookstore.data.model;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.Book;
import com.comp3350_group10.bookstore.data.User;
import com.comp3350_group10.bookstore.business.Data_Handler.DataHandler;

import junit.framework.TestCase;

public class DataHandlerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public static void testSetPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.setPrice(book,50);
        assertEquals(book.getPrice(),50);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.setPrice(book_1,0);
        assertEquals(book_1.getPrice(),0);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.setPrice(book_2,0);
    }

    public static void testIncrementPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.incrementPrice(book);
        assertEquals(book.getPrice(),26);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.incrementPrice(book_1);
        assertEquals(book_1.getPrice(),11);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.incrementPrice(book_2);

    }

    public static void testDecrementPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.decrementPrice(book);
        assertEquals(book.getPrice(),24);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.decrementPrice(book_1);
        assertEquals(book_1.getPrice(),9);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.decrementPrice(book_2);
    }

    public static void testSetStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.setStock(book,10);
        assertEquals(book.getStock(),10);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.setStock(book_1,20);
        assertEquals(book_1.getStock(),20);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.setStock(book_2,10);
    }

    public static void testIncrementStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.incrementStock(book);
        assertEquals(book.getStock(),3);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.incrementStock(book_1);
        assertEquals(book_1.getStock(),4);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.incrementStock(book_2);
    }

    public static void testDecrementStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2, R.drawable.lotr);
        DataHandler handler = new DataHandler();
        handler.decrementStock(book);
        assertEquals(book.getStock(),1);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3, R.drawable.lotr);
        handler.decrementStock(book_1);
        assertEquals(book_1.getStock(),2);
        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        Book book_2 = null;
        handler.decrementStock(book_2);
    }

    //CAN'T TEST THIS SINCE THE USER IS PRIVATE AND NO ACCESSIBILITY TO IT
    public static void testIsCurrentUserManager() {

    }
    //THIS AS WELL
    public static void testLogOut() {
    }

    public static void testChangePassword(){
        User currentUser = new User("Tom", "email@gmail.com","12345678", UserType.Manager);
        DataHandler handler = new DataHandler();

        handler.changePassword("12345678","","");
    }
}