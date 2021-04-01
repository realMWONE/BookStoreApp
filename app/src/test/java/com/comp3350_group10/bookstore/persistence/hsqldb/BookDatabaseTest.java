package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.utils.TestUtils;

import junit.framework.TestCase;

import org.junit.Assert;

import java.io.File;
import java.util.List;

public class BookDatabaseTest extends TestCase {

    private File tempDB;

    private IBookDatabase bookDatabase;

    public void setUp() throws Exception {
        super.setUp();
        this.tempDB = TestUtils.copyDB();
        bookDatabase = new BookDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
        System.out.println("path = "+this.tempDB.getAbsolutePath().replace(".script", ""));
    }

    public void tearDown() throws Exception {
        this.tempDB.delete();
    }

    //This function is internally WRONGGGG

    public void testFindBook() {
        List<IBook> listBook = bookDatabase.findBook("Diary");
        String[] expectedResult={
                "Diary of Wimpy Kid: The Getaway",
                "Diary of Wimpy Kid: Double Down",
        };
        Assert.assertEquals(expectedResult.length,listBook.size());
    }

    public void testGetBooks() {
        List<IBook> result = bookDatabase.getBooks();
        Assert.assertEquals(15,result.size());
    }

    public void testInsertBook() {
        IBook book = new Book("Heaven","1234567890123",20
                ,1000,"1 Jan 2001","Duy","Novel",0,321123);
        int beforeSize = bookDatabase.getBooks().size();
        IBook newBook = bookDatabase.insertBook(book);
        int afterSize = bookDatabase.getBooks().size();
        //This assert works
        Assert.assertEquals(beforeSize+1,afterSize);
        List<IBook> result = bookDatabase.findBook("Duy");
        Assert.assertEquals(1,result.size());
        Assert.assertEquals("Heaven",result.get(0).getBookName());
    }

    public void testUpdateBook() {
        IBook book = new Book("Eclipse","2551819816185",20,2000,"07 April 2008"
                ,"Stephenie Meyer","Romance",30,700116);
        bookDatabase.updateBook(book);
        List<IBook> result = bookDatabase.findBook("Eclipse");
        //guarantee there is only one kind of this book
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(20,result.get(0).getStock());
        Assert.assertEquals(2000,result.get(0).getPrice());
        Assert.assertEquals(30,result.get(0).getReserve());
    }

    public void testDeleteBook() {
        IBook book = new Book("Eclipse","2551819816185",20,2000,"07 April 2008"
                ,"Stephenie Meyer","Romance",30,700116);
        int beforeSize = bookDatabase.getBooks().size();
        bookDatabase.deleteBook(book);
        int afterSize = bookDatabase.getBooks().size();
        Assert.assertEquals(afterSize,beforeSize-1);

    }
}