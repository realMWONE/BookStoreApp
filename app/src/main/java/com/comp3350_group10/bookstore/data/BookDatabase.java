/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.data;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class BookDatabase {
    //List for our book database which would store the list of Book objects
    private static List<Book> bookList;
    //Constructor
    public BookDatabase() {
        //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    private void createDatabase(){
        bookList.add(new Book("6783903121501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 2630, 10));
        bookList.add(new Book("6783903121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 2650, 10));
        bookList.add(new Book("6783903121503", "The Da Vinci Code", "Dan Brown", 3000, 20));
        bookList.add(new Book("6783903121504", "Angels and Demons", "Dan Brown", 3000, 5));
    }


    /**
     * findBooks: Finds and returns the book objects based on their book ISBN
     * @param isbn
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Book findBook(String isbn) {
//        for(Book i:bookList ) {
//
//        }
        return bookList.stream().filter(book -> isbn.equals(book.getBookIsbn())).findFirst().orElse(null);
    }

}

//   public static List<Book> findBooks(String searchTerm) {
//       List1 = findByISBN(searchTerm);
//       List2 = findByAuthor(searchTerm);
//       List3 = findByTitle(searchTerm);
//       MasterList = List1 + List2 + List3;
//       MasterList.removeDuplicates();
//       return MasterList;
//   }




