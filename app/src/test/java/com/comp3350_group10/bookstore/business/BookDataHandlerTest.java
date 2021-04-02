package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;


public class BookDataHandlerTest{
    private File tempDB;
    private BookDataHandler handler;

    @Before
    public void setUp() throws Exception {
        this.tempDB = TestUtils.copyDB();
        final IBookDatabase bookDatabase = new BookDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.handler = new BookDataHandler(bookDatabase);
    }

    @After
    public void tearDown() throws Exception {
        this.tempDB.delete();
    }

    @Test
    //didn't test for the throw error
    public void testFindBooks() {
        final List<IBook> book;
        try {
            book = handler.findBooks("The Da Vinci Code");
            Assert.assertNotNull("Book should not be null", book);
            Assert.assertEquals("The Da Vinci Code",book.get(0).getBookName());
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSetPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.setPrice(book,3000);
        List<IBook> result;

        try {
            result = handler.findBooks("2551819816185");
            Assert.assertEquals(1,result.size());
            Assert.assertEquals(3000,result.get(0).getPrice());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIncrementPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        this.handler.incrementPrice(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = this.handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2781,result.get(0).getPrice());
    }

    @Test
    public void testDecrementPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        this.handler.decrementPrice(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = this.handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2779,result.get(0).getPrice());
    }

    @Test
    public void testSetStock() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        //TO DO: test the exception to be thrown
        try {
            handler.setStock(book,50);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(50,result.get(0).getStock());
    }

    @Test
    public void testIncrementStock() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.incrementStock(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3,result.get(0).getStock());
    }

    @Test
    public void testDecrementStock() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.decrementStock(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1,result.get(0).getStock());
    }
}