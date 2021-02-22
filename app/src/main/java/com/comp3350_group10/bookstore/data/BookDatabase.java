/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.data;
import java.util.List;

public class BookDatabase {

    //List for our book database which would store the list of Book objects
    List<Book> bookList;

    //Constructor
    public BookDatabase() {
        //Calling createDatabase method here so everytime the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    public void createDatabase(){
        bookList.add(new Book("6783903121501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 26, 10));
        bookList.add(new Book("6783903121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 26, 10));
        bookList.add(new Book("6783903121503", "The Da Vinci Code", "Dan Brown", 30, 20));
        bookList.add(new Book("6783903121504", "Angels and Demons", "Dan Brown", 30, 5));
    }


    /**
     * findBooks: Finds and returns the book objects based on their book ISBN
     * @param isbn
     * @return
     */
//    public static Book findBook(String isbn) {
//
// todo : Work in progress
//   }
}



