package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import junit.framework.TestCase;

import org.junit.Assert;

import java.io.File;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class BookDataHandlerTest extends TestCase {
    private File tempDB;

    //private IBookDatabase bookDatabase;

    private BookDataHandler handler;
    public void setUp() throws Exception {
        super.setUp();
        this.tempDB = TestUtils.copyDB();
        IBookDatabase bookDatabase = new BookDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
        handler = new BookDataHandler(bookDatabase);


    }

    public void tearDown() throws Exception {
        this.tempDB.delete();
    }

    public void testBookNotFound(){


    }

    //didn't test for the throw error
    public void testFindBooks() {
        List<IBook> result = null;
        //TO DO: test the exception to be thrown
        try {
            result = handler.findBooks("123412");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*Assert.assertEquals(1,result.size());
        //Diary of Wimpy Kid: Double Down','4578932145250',12,1280,
        // '21 February 2006','Jeff Kinney','Comedy',3,700003
        Assert.assertEquals("Diary of Wimpy Kid: Double Down",result.get(0).getBookName());
        Assert.assertEquals("4578932145250", result.get(0).getBookIsbn());
        Assert.assertEquals(12,result.get(0).getStock());
        Assert.assertEquals(1280,result.get(0).getPrice());
        Assert.assertEquals("21 February 2006",result.get(0).getDate());
        Assert.assertEquals("Jeff Kinney",result.get(0).getBookAuthor());
        Assert.assertEquals("Comedy",result.get(0).getGenre());
        Assert.assertEquals(3,result.get(0).getReserve());
        Assert.assertEquals(700003,result.get(0).getImage());*/
    }
    public void testSetPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.setPrice(book,3000);
        List<IBook> result = null;
        //TO DO: test the exception to be thrown
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(3000,result.get(0).getPrice());
    }

    public void testIncrementPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.incrementPrice(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2781,result.get(0).getPrice());
    }

    public void testDecrementPrice() {
        IBook book = new Book("Eclipse","2551819816185",2,2780,"07 April 2008"
                ,"Stephenie Meyer","Romance",3,700116);
        handler.decrementPrice(book);
        //TO DO: test the exception to be thrown
        List<IBook> result=null;
        try {
            result = handler.findBooks("2551819816185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2779,result.get(0).getPrice());
    }

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