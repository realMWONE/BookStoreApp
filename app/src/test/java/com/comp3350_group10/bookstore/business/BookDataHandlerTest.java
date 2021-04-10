package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabaseStub;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BookDataHandlerTest extends TestCase {

    //we will use a stub
    private BookDataHandler dataHandler;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        IBookDatabase databaseStub = new BookDatabaseStub();
        this.dataHandler = new BookDataHandler(databaseStub);

        //we will use all the functions available in the BookDatabaseStub() to test
    }
    @After
    public void tearDown() throws Exception {
        dataHandler=null;
    }
    @Test
    public void testFindBooks() {
        List<IBook> result_1 = dataHandler.findBooks("The");
        assertEquals("there should be 1 results",1,result_1.size());
        List<IBook> result_2 = dataHandler.findBooks("Harry");
        assertEquals("there should be 2 results",2,result_2.size());
        List<IBook> result_3 = dataHandler.findBooks("Diary of Wimpy Kid");
        assertEquals("there should be 2 results",2,result_3.size());
    }
    @Test
    public void testSetPrice() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setPrice(book_1,1000);
        assertEquals("The new price should be 1000",1000,book_1.getPrice());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setPrice(book_2,2000);
        assertEquals("The new price should be 2000",2000,book_2.getPrice());
        IBook book_3 = new Book("Eclipse","2551819816185",2,2780,"07 April 2008","Stephenie Meyer","Romance",3,700116);
        dataHandler.setPrice(book_3,-5);
        assertEquals("The price shouldn't be negative",2780,book_3.getPrice());
    }
    @Test
    public void testSetStock() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setStock(book_1,50);
        assertEquals("The new stock should be 50",50,book_1.getStock());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setStock(book_2,-1);
        assertEquals("The new quantity should not be negative",3,book_2.getStock());
    }

    //Have to remind me of how should we carry out these test because they look.. awkward
    @Test
    public void testIncrementStock() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        assertNotNull("The book should not be null",book_1);
        dataHandler.incrementStock(book_1);
        assertEquals("The new stock should be 13",13,book_1.getStock());
    }
    @Test
    public void testDecrementStockException(){
        try{
            IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",0,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
            dataHandler.decrementStock(book_1);
            System.out.println("yes");
        }
        catch (NegativeStockException exception){
            assertEquals("Stock cannot be less than 0.",exception.getMessage());
        }
    }
    @Test
    public void testDecrementStock() {
        try{
            IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
            assertNotNull("The book should not be null",book_1);
            dataHandler.decrementStock(book_1);
            assertEquals("The new stock should be 11",11,book_1.getStock());
        }
        catch (NegativeStockException exception){
            assertEquals("It should never reach the catch statement",exception.getMessage());
        }

    }
}
