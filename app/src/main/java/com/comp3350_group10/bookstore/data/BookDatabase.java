/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.data;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class BookDatabase {

    //Constructor
    public BookDatabase() {
        //Calling createDatabase method here so everytime the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    public void createDatabase(){
        //List for our book database which would store the list of Book objects
        List<Book> bookList = null;
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Book findBook(List<Book> booklist, String isbn) {
        return booklist.stream().filter(book -> isbn.equals(book.getBookIsbn())).findFirst().orElse(null);
   }
}



