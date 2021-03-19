package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.R;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;

import junit.framework.TestCase;

public class BookDataHandlerTest extends TestCase {
    static BookDataHandler handler;
    Book normalBook, zeroBook, nullBook;
    BookDatabase bookDB;

    public void setUp() throws Exception {
        super.setUp();
        handler = new BookDataHandler();
        normalBook = new Book("Harry Potter and the Philosopher's Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,R.drawable.philosophers_stone);
        zeroBook = new Book("Legend of Zero","0",0,0,"","Zero","Novel",0, R.drawable.harry_potter_and_the_chamber_of_secrets);
        nullBook  = null;
        bookDB = new BookDatabase();

        bookDB.insertBook(new Book("The Pain of Not Knowing Database and Can't Help the Team to Debug","12345678",420,69420,"15 January 1997","Matt Wong","Fictional",5, R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookDB.insertBook(new Book("Why is the Database Not Working With Logic","67890123",123,5000,"19 March 2021","Group Ten","Documentary",10, R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookDB.insertBook(new Book("2012 End of The World","20122012",2012,2012,"1 January 2012","Mayans","",12, R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookDB.insertBook(new Book("How to Write Not So Good Unit Tests","23456789",223,990,"29 December 2000","Some CS Guy","Educational",30, R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookDB.insertBook(normalBook);
        bookDB.insertBook(zeroBook);
    }

    public void tearDown() throws Exception {
    }

    public void testSetPrice() {
        //normal case
        handler.setPrice(normalBook,5000);
        assertEquals(normalBook.getPrice(),5000);

        //negative case
        try {
            handler.setPrice(zeroBook, -1);
        }catch (Exception e){
            fail( "Exception was not caught" );
        }

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        try {
            handler.setPrice(nullBook,0);
        } catch( Exception e ) {
            fail( "Exception was not caught" );
        }
    }

    public void testIncrementPrice() {
        //normal case
        handler.incrementPrice(normalBook);
        assertEquals(normalBook.getPrice(),2631);

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        try {
            handler.incrementPrice(nullBook);
        } catch( Exception e ) {
            fail( "Exception was not caught" );
        }
    }

    public void testDecrementPrice() {
        //Book 1
        handler.decrementPrice(normalBook);
        assertEquals(normalBook.getPrice(),2629);

        //negative case
        try {
            handler.decrementPrice(zeroBook);
            assertEquals(zeroBook.getPrice(), 0);
        }catch (Exception e){
            fail( "Exception was not caught");
        }

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        try {
            handler.decrementPrice(nullBook);
        } catch( Exception e ) {
            fail( "Exception was not caught" );
        }
    }

    public void testSetStock() throws ClassNotFoundException {
        //Book 1
        handler.setStock(normalBook,10);
        assertEquals(normalBook.getStock(),10);

        //negative case
        try {
            handler.setStock(zeroBook,-1);
            assertEquals(zeroBook.getStock(),0);
        }catch (Exception e){
            fail( "Exception was not caught");
        }

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        try {
            handler.setStock(nullBook, 0);
            assertNull(nullBook);
        } catch( Exception e ) {
            fail( "Exception was not caught" );
        }
    }

    public void testIncrementStock() {
        //Book 1
        handler.incrementStock(normalBook);
        assertEquals(normalBook.getStock(),13);

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        handler.incrementStock(nullBook);
        assertNull(nullBook);
    }

    public void testDecrementStock() {
        //Book 1
        handler.decrementStock(normalBook);
        assertEquals(normalBook.getStock(),11);
        //negative case
        try {
            handler.decrementStock(zeroBook);
            assertEquals(zeroBook.getStock(),0);
        }catch (Exception e){
            fail( "Exception was not caught");
        }

        //Book Null (testing for null books/objects)
        //Test for null pointer exception handled
        try {
            handler.setStock(nullBook, 0);
            assertNull(nullBook);
        } catch( Exception e ) {
            fail( "Exception was not caught" );
        }
    }


    public void testFindBooks() throws ClassNotFoundException {
        bookDB.findBook("Legend");
    }
}