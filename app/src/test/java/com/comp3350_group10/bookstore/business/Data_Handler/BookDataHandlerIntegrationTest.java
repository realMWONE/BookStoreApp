package com.comp3350_group10.bookstore.business.Data_Handler;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class BookDataHandlerIntegrationTest extends TestCase {
    BookDataHandler handler;
    ArrayList<IBook> bookList;
    IBook nullBook, nonExistBook, testBook, testBook_DB;
    BookDatabase db;
    int restoreValue;
    public void setUp() throws Exception {
        super.setUp();
        handler = new BookDataHandler();    //real db
        db = new BookDatabase(Main.getDBPath());
        testBook = new Book("Harry Potter and the Philosopher's Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,R.drawable.philosophers_stone);

        //equivalent of the real database
        bookList = new ArrayList<>();
        bookList.add(testBook);
        bookList.add(new Book("Harry Potter and the Chamber of Secrets","3820484736373",14,2640,"21 April 1998","J.K.Rowling","Novel",5,R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookList.add(new Book("The Da Vinci Code","0218574629654",10,2780,"21 October 2007","Dan Brown","Mystery",3,R.drawable.the_da_vinci_code));
        bookList.add(new Book("Angels and Demons","2865926595295",11,2780,"15 June 2009","Dan Brown","Mystery",5,R.drawable.angels_demons));
        bookList.add(new Book("Diary of Wimpy Kid: The Getaway","5987450215825",13,1250,"16 January 2005","Jeff Kinney","Comedy",4, R.drawable.diary_of_wimpy_kid_the_getaway));
        bookList.add(new Book("Diary of Wimpy Kid: Double Down","4578932145250",12,1280,"21 February 2006","Jeff Kinney","Comedy",3,R.drawable.diary_of_wimpy_kid_double_down));
        bookList.add(new Book("Harry Potter and the Prisoner of Azkaban","6003255121507",14,2680,"22 March 1999","J.K.Rowling","Novel",2,R.drawable.prisoner_of_azkaban));
        bookList.add(new Book("Harry Potter and the Goblet of Fire","6783908521508",13,2680,"06 June 2001","J.K.Rowling","Novel",5,R.drawable.harry_potter_and_the_goblet_fire));
        bookList.add(new Book("Harry Potter and the Order of Phoenix","6588503121509",12,2680,"01 April 2003","J.K.Rowling","Novel",4,R.drawable.harry_potter_and_the_order_of_the_phoenix));
        bookList.add(new Book("Harry Potter and the Half-Blood Prince","6654684858510",11, 2680,"03 July 2005","J.K.Rowling","Novel",3,R.drawable.harry_potter_and_the_half_blood_prince));
        bookList.add(new Book("Harry Potter and the Deathly Hallows","6874684025221",10,2680,"25 March 2007","J.K.Rowling","Novel",6,R.drawable.harry_potter_and_the_deathly_hallows));
        bookList.add(new Book("Harry Potter and the Cursed Child","5354684656848",2,2680,"21 March 2009","J.K.Rowling","Novel",5,R.drawable.harry_potter_and_the_cursed_child));
        bookList.add(new Book("Twilight","2510323255565",3,2730,"22 March 2005","Stephenie Meyer","Romance",4,R.drawable.twilight));
        bookList.add(new Book("Eclipse","2551819816185",2,2780,"07 April 2008","Stephenie Meyer","Romance",3,R.drawable.eclipse));
        bookList.add(new Book("New Moon","2516511685000",4,2780,"23 February 2010","Stephenie Meyer","Romance",3,R.drawable.new_moon));

        nullBook = null;
        nonExistBook = new Book("How to Write Not So Good Unit Tests", "23456789", 223, 990, "29 December 2000", "Some CS Guy", "Educational", 30, R.drawable.harry_potter_and_the_chamber_of_secrets);
        
        //****************** default test case: uses testBook ************************************//
        testBook_DB = db.findBook(testBook.getBookIsbn()).get(0);       //points to db copy of testBook
                                                                        //search ISBN, should only return 1 book, so the 0th index is testbook in database

    }

    public void tearDown()  {
    }

    public void testSetPrice() throws Exception {
        setUp();
        restoreValue = testBook.getPrice();                                 //save restoreValue value for restoring

        //normal case
        handler.setPrice(testBook, 5000);
        assertEquals(testBook_DB.getPrice(), 5000);  
        
        handler.setPrice(testBook,restoreValue); //restore restoreValue value

        //negative case
        try {
            handler.setPrice(testBook, -1);
            assertNotSame(testBook_DB.getPrice(), -1);  //make sure didn't modify data in database
            handler.setPrice(testBook, restoreValue);    //restore
        } catch (Exception e) {
            fail("Exception was not caught");
        }

        //non existing book
       try{
           handler.setPrice(nonExistBook, 100);
       }catch (Exception e){
           fail("Exception was not caught");
       }
    }

    public void testIncrementPrice() throws Exception {
        setUp();
        restoreValue = testBook.getPrice();                                 //save restoreValue value for restoring

        //normal case
        handler.incrementPrice(bookList.get(0));
        assertEquals(testBook_DB.getPrice(), restoreValue+1);

        handler.setPrice(testBook,restoreValue); //restore restoreValue value

        //non existing book
        try{
            handler.incrementPrice(nonExistBook);
        }catch (Exception e){
            fail("Exception was not caught");
        }
    }

    public void testDecrementPrice() throws Exception {
        setUp();
        restoreValue = testBook.getPrice();                                 //save restoreValue value for restoring

        //normal case
        handler.decrementPrice(bookList.get(0));
        assertEquals(testBook.getPrice(), restoreValue-1);

        handler.setPrice(testBook,restoreValue); //restore restoreValue value

        //negative case
        try {
            handler.setPrice(testBook, 0);   //first set to 0
            handler.decrementPrice(testBook);       //decrement would make it -1

            assertNotSame(testBook_DB, -1);  //make sure didn't modify data in database

            handler.setPrice(testBook, restoreValue);    //restore
        } catch (Exception e) {
            fail("Exception was not caught");
        }

        //non existing book
        try{
            handler.decrementPrice(nonExistBook);
        }catch (Exception e){
            fail("Exception was not caught");
        }
    }

    public void testSetStock() throws Exception {
        setUp();
        restoreValue = testBook.getStock();                                 //save restoreValue value for restoring

        //normal case
        handler.setStock(testBook, 200);
        assertEquals(testBook_DB.getPrice(), 200);

        handler.setStock(testBook, restoreValue); //restore restoreValue value

        //negative case
        try {
            handler.setStock(testBook, -1);
            assertNotSame(testBook_DB, -1);  //make sure didn't modify data in database
            handler.setStock(testBook, restoreValue);    //restore
        } catch (Exception e) {
            fail("Exception was not caught");
        }

        //non existing book
        try{
            handler.setStock(nonExistBook, 100);
        }catch (Exception e){
            fail("Exception was not caught");
        }
    }

    public void testIncrementStock() throws Exception {
        setUp();
        restoreValue = testBook.getStock();                                 //save restoreValue value for restoring

        //normal case
        handler.incrementStock(testBook);
        assertEquals(testBook_DB.getStock(), 200);

        handler.setStock(testBook, restoreValue); //restore restoreValue value

        //non existing book
        try{
            handler.incrementStock(nonExistBook);
        }catch (Exception e){
            fail("Exception was not caught");
        }
    }

    public void testDecrementStock() throws Exception {
        setUp();
        restoreValue = testBook.getStock();                                 //save restoreValue value for restoring

        //Book 1
        handler.decrementStock(testBook);
        assertEquals(testBook_DB.getStock(), 11);
        //negative case
        try {
            handler.setStock(testBook, 0);   //set to 0
            handler.decrementStock(testBook);         //decrement would make stock -'ve
            handler.setStock(testBook, restoreValue);
        } catch (Exception e) {
            fail("Exception was not caught");
        }

        //non existing book
        try{
            handler.decrementStock(nonExistBook);
        }catch (Exception e){
            fail("Exception was not caught");
        }
    }


    public void testFindBooks() throws Exception {
        setUp();
        //regular case
        List<IBook> result = handler.findBooks("Harry Potter");
        for (IBook b : result) {
            //when search "harry potter" all books should either have harry or potter or both
            assertTrue(b.getBookName().toLowerCase().contains("harry") ||
                    b.getBookName().toLowerCase().contains("potter"));
        }
        assertEquals(8, result.size());                 //there are 8 harry potters in the database

        //relevancy test
        result = handler.findBooks("harry potter order phoenix twilight");
        assertSame(result.get(0), bookList.get(8));                 //"Harry Potter Order of the Phoenix" should be first with 4 words match
                                                                    //all harry potters in middle
        assertSame(result.get(result.size()-1), bookList.get(12));  //last should be "twilight" with only 1 word match


        //test search scope with prefix
        result = handler.findBooks("da br");
        assertEquals(result.size(),2);  //"the DA vinci code" and "angels and demon" by DAn BRown

        //capital and delimiter split test
        result = handler.findBooks("ThE........:::::,,, DA,,----   VinCi :::,,,CODe");
        assertEquals(result.get(0), bookList.get(2));

        //non-ascii test
        result = handler.findBooks("今次仆街了");
        assertEquals(result.size(), 0);         //shouldn't have any result

        //isbn search
        result = handler.findBooks(testBook.getBookIsbn());    //isbn for testbook
        assertTrue(result.contains(testBook));

        //author search
        result = handler.findBooks("Dan Brown");
        assertTrue(result.contains(bookList.get(2)) && result.contains(bookList.get(3)));
    }

    public void testAll() throws Exception {
        testDecrementPrice();
        testDecrementStock();
        testFindBooks();
        testIncrementPrice();
        testIncrementStock();
        testSetPrice();
        testSetStock();
    }
}