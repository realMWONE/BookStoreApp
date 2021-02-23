/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.data;

import java.util.List;

public class BookDatabase implements IBookDatabase{
    //List for our book database which would store the list of Book objects
    private static List<Book> bookList;
    //Constructor
    public BookDatabase() {
        //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    public static void setBookList(List<Book> bookList) {
        BookDatabase.bookList = bookList;
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
     * @param searchTerm
     * @return
     */
    public List<Book> findBook(String searchTerm) {

        //Lists which contains book objects related to specific search terms
        List<Book> findByISBN = findByISBN(searchTerm);
        List<Book> findByAuthor = findByAuthor(searchTerm);
        List<Book> findByTitle = findByTitle(searchTerm);

        //Filtering by removing duplicates and adding them all into a single list which has elements of the search term
        List<Book> bookResult = null;
        for(Book book: findByISBN){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(Book book: findByAuthor){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(Book book: findByTitle){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }

        return bookResult;
    }

    /**
     * findByISBN: Finds books from our bookList by ISBN
     * @param isbn
     */
    private static List<Book> findByISBN(String isbn){
        List<Book> bookIsbn = null;
        if(isbn != null){
            //Going through all the bookList
            for(Book book: bookList){
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if(book.getBookIsbn().contains(isbn)){
                    bookIsbn.add(book);
                }
            }
        }
        return bookIsbn;
    }

    /**
     * findByAuthor: Finds books from our bookList by author
     * @param author
     */
    private static List<Book> findByAuthor(String author){
        List<Book> bookAuthor = null;
        if(author != null){
            //Going through all the bookList
            for(Book book: bookList){
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if(book.getBookAuthor().contains(author)){
                    bookAuthor.add(book);
                }
            }
        }
        return bookAuthor;
    }

    /**
     * findByTitle: Finds books from our bookList by title
     * @param title
     */
    private static List<Book> findByTitle(String title){
        List<Book> bookTitle = null;
        if(title != null){
            //Going through all the bookList
            for(Book book: bookList) {
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if (book.getBookName().contains(title)) {
                    bookTitle.add(book);
                }
            }
        }
        return bookTitle;
    }
}





