/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.data;
import com.comp3350_group10.bookstore.data.Book;

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
        bookList.add(new Book("6783903121505", "Diary of Wimpy Kid:The Getaway", "Jeff Kinney", 2500, 10));
        bookList.add(new Book("6783903121506", "Diary of Wimpy Kid: Double Down", "Jeff Kinney", 2500, 10));
        bookList.add(new Book("6783903121507", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2630, 10));
        bookList.add(new Book("6783903121508", "Harry Potter and the Goblet of Fire", "J.K. Rowling", 2630, 10));
        bookList.add(new Book("6783903121509", "Harry Potter and the Order of Phoenix", "J.K. Rowling", 2630, 10));
        bookList.add(new Book("6783903121510", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2630, 10));
        bookList.add(new Book("6783903121511", "Harry Potter and the Deathly Hallows", "J.K. Rowling", 2630, 10));
    }


    /**
     * findBooks: Finds and returns the book objects based on their book ISBN
     * @param isbn
     * @return
     */
    public static String findBook(String isbn) {
        String findByISBN = findByISBN(isbn);
        String findbyAuthor = findByAuthor(isbn);
        String findByTitle = findByTitle(isbn);
        String bookInfo = findByISBN + findbyAuthor + findByTitle;
        return bookInfo;
    }

    private static String findByISBN(String isbn){
        //Should find books by ISBN
        String bookIsbn = null;
        for(Book book: bookList){
            if(book.getBookIsbn().equals(isbn)){
                bookIsbn = book.getBookIsbn();
            }
        }
        return bookIsbn;
    }

    private static String findByAuthor(String isbn){
        //Should find books by book author
        String bookAuthor = null;
        for(Book book: bookList){
            if(book.getBookIsbn().equals(isbn)){
                bookAuthor = book.getBookAuthor();
            }
        }
        return bookAuthor;
    }
    private static String findByTitle(String isbn){
        //Should find books by book title
        String bookTitle = null;
        for(Book book: bookList){
            if(book.getBookIsbn().equals(isbn)){
                bookTitle = book.getBookName();
            }
        }
        return bookTitle;
    }
}





