package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.Book;
import com.comp3350_group10.bookstore.data.User;

import junit.framework.TestCase;

public class DataHandlerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testSetPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.setPrice(book,50);
        assertEquals(book.getPrice(),50);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.setPrice(book_1,0);
        assertEquals(book_1.getPrice(),0);
    }

    public void testIncrementPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.incrementPrice(book);
        assertEquals(book.getPrice(),26);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.incrementPrice(book_1);
        assertEquals(book_1.getPrice(),11);
    }

    public void testDecrementPrice() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.decrementPrice(book);
        assertEquals(book.getPrice(),24);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.decrementPrice(book_1);
        assertEquals(book_1.getPrice(),9);
    }

    public void testSetStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.setStock(book,10);
        assertEquals(book.getStockAmount(),10);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.setStock(book_1,20);
        assertEquals(book_1.getStockAmount(),20);
    }

    public void testIncrementStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.incrementStock(book);
        assertEquals(book.getStockAmount(),3);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.incrementStock(book_1);
        assertEquals(book_1.getStockAmount(),4);
    }

    public void testDecrementStock() {
        //Book 1
        Book book = new Book("1234567891111","Harry Potter","J.K",25,2);
        DataHandler handler = new DataHandler();
        handler.decrementStock(book);
        assertEquals(book.getStockAmount(),1);
        //Book 2
        Book book_1 = new Book("12234789121","Doraemon","Fujiko",10,3);
        handler.decrementStock(book_1);
        assertEquals(book_1.getStockAmount(),2);
    }

    //CAN'T TEST THIS SINCE THE USER IS PRIVATE AND NO ACCESSIBILITY TO IT
    public void testIsCurrentUserManager() {

    }
    //THIS AS WELL
    public void testLogOut() {
    }
}