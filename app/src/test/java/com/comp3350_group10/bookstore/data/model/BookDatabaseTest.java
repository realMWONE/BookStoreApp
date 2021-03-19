//package com.comp3350_group10.bookstore.data.model;
//
//import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
//import com.comp3350_group10.bookstore.persistence.IBook;
//import com.comp3350_group10.bookstore.persistence.IBookDatabase;
//
//import junit.framework.TestCase;
//
//import java.util.List;
//
////HARDDDDDDDDDDDDD
//public class BookDatabaseTest extends TestCase {
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    public void tearDown() throws Exception {
//    }
//
//    public static void testSetBookList() {
//        IBookDatabase database = new BookDatabase();
//        assertNotNull(database);
//    }
//
//    public static void testFindBook() {
//        BookDatabase database = new BookDatabase();
//        /*
//        ("6783903121501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 2630, 10));
//        ("6783903121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 2650, 10));
//        ("6783903121503", "The Da Vinci Code", "Dan Brown", 3000, 20));
//        ("6783903121504", "Angels and Demons", "Dan Brown", 3000, 5));
//        ("6783903121505", "Diary of Wimpy Kid:The Getaway", "Jeff Kinney", 2500, 10));
//        ("6783903121506", "Diary of Wimpy Kid: Double Down", "Jeff Kinney", 2500, 10));
//        ("6783903121507", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2630, 10));
//        ("6783903121508", "Harry Potter and the Goblet of Fire", "J.K. Rowling", 2630, 10));
//        ("6783903121509", "Harry Potter and the Order of Phoenix", "J.K. Rowling", 2630, 10));
//        ("6783903121510", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2630, 10));
//        ("6783903121511", "Harry Potter and the Deathly Hallows", "J.K. Rowling", 2630, 10));
//        */
//        List<IBook> list = database.findBook("J.K. Rowling");
//        System.out.println(list.size()+"");
//        for(int i=0;i<list.size();i++){
//            assertEquals(list.get(i).getBookAuthor(),"J.K. Rowling");
//        }
//    }
//}