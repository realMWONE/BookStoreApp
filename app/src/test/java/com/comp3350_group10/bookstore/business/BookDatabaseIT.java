package com.comp3350_group10.bookstore.business;

import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookDatabaseIT {
    private BookDataHandler dataHandler;
    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final IBookDatabase persistence = new BookDatabase(this.tempDB.getAbsolutePath().replace(".script",""));
        this.dataHandler = new BookDataHandler(persistence);
    }

    @Test
    public void testFindBooks(){
        final List<IBook> bookList;
        final IBook book;
        bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets");
        book = bookList.get(0);
        assertNotNull("The book entered should not be null", bookList);
        assertTrue(book.getBookName().equals(bookList.get(0).getBookName()));
        System.out.println(book.getBookName());
        System.out.println("Finished testing BookDataHandler");
    }

    @Test
    public void testSetBookPrice(){
        final IBook book;
        final List<IBook> bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets");
        book = bookList.get(0);
        dataHandler.setPrice(book, 3000);
        assertNotNull("The book entered should not be null", bookList);
        assertEquals(3000, book.getPrice());
        System.out.println(book.getPrice());
    }

    @Test
    public void testSetBookStock(){
        final IBook book;
        final List<IBook> bookList = dataHandler.findBooks("Harry Potter and the Chamber of Secrets");
        book  = bookList.get(0);
        dataHandler.setStock(book, 2);
        assertNotNull("The book entered should not be null", bookList);
        assertEquals(2, book.getStock());
        System.out.println(book.getStock());
    }

    @After
    public void tearDown(){
        this.tempDB.delete();//resets DB
    }
}
