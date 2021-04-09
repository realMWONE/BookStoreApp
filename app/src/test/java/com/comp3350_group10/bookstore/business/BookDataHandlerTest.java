package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabaseStub;

import junit.framework.TestCase;

import java.util.List;

public class BookDataHandlerTest extends TestCase {

    //we will use a stub
    private BookDataHandler dataHandler;
    public void setUp() throws Exception {
        super.setUp();
        IBookDatabase databaseStub = new BookDatabaseStub();
        this.dataHandler = new BookDataHandler(databaseStub);

        //we will use all the functions available in the BookDatabaseStub() to test
    }

    public void tearDown() throws Exception {
        dataHandler=null;
    }

    public void testFindBooks() {
        List<IBook> result_1 = dataHandler.findBooks("The");
        assertEquals("there should be 1 results",1,result_1.size());
        List<IBook> result_2 = dataHandler.findBooks("Harry");
        assertEquals("there should be 2 results",2,result_2.size());
        List<IBook> result_3 = dataHandler.findBooks("Diary of Wimpy Kid");
        assertEquals("there should be 2 results",2,result_3.size());
    }

    public void testSetPrice() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setPrice(book_1,1000);
        assertEquals("The new price should be 1000",1000,book_1.getPrice());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setPrice(book_2,2000);
        assertEquals("The new price should be 2000",2000,book_2.getPrice());
    }

    public void testSetStock() {
        IBook book_1 = new Book("Harry Potter and the Philosopher Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,700132);
        dataHandler.setStock(book_1,50);
        assertEquals("The new stock should be 50",50,book_1.getStock());
        IBook book_2 = new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,700141);
        dataHandler.setStock(book_2,100);
        assertEquals("The new price should be 100",100,book_2.getStock());
    }

    //Have to remind me of how should we carry out these test because they look.. awkward
    public void testIncrementStock() {

    }

    public void testDecrementStock() {
    }
}