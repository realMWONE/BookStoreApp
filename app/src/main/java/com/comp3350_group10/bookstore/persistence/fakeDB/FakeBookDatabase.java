/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.persistence.fakeDB;

import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeBookDatabase implements IBookDatabase {
    //List for our book database which would store the list of Book objects
    private List<IBook> bookList;

    //Constructor
    public FakeBookDatabase() {
        //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    private void createDatabase(){
        bookList = new ArrayList<>();
    }


    /**
     * findBooks: Finds and returns the book objects based on their book ISBN
     * @param searchTerm
     * @return
     */
    public List<IBook> findBook(String searchTerm) {

        //Lists which contains book objects related to specific search terms
        List<IBook> findByISBN = findByISBN(searchTerm);
        List<IBook> findByAuthor = findByAuthor(searchTerm);
        List<IBook> findByTitle = findByTitle(searchTerm);

        //Filtering by removing duplicates and adding them all into a single list which has elements of the search term
        List<IBook> bookResult = new ArrayList<>();

        for(IBook book: findByISBN){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(IBook book: findByAuthor){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(IBook book: findByTitle){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }

        return bookResult;
    }

    @Override
    public List<IBook> getBooks() throws ClassNotFoundException {
        return null;
    }

    @Override
    public IBook insertBook(IBook book) throws ClassNotFoundException {
        bookList.add(book);
        return book;
    }

    @Override
    public void updateBook(IBook book) throws ClassNotFoundException {

    }

    @Override
    public void deleteBook(IBook book) throws ClassNotFoundException {

    }

    /**
     * findByISBN: Finds books from our bookList by ISBN
     * @param isbn
     */
    private List<IBook> findByISBN(String isbn){
        List<IBook> bookIsbn = new ArrayList<>();
        if(isbn != null){
            //Going through all the bookList
            for(IBook book: bookList){
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if(book.getBookIsbn().startsWith(isbn)){
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
    private List<IBook> findByAuthor(String author){
        List<IBook> bookAuthor = new ArrayList<>();
        String[] split;
        if(author != null){
            //Going through all the bookList
            for(IBook book: bookList) {
                //***CHANGED***:
                // now instead of pulling whole string and try to match with the search term,
                // we split the term by delimeters and try to match each search term with each data term
                //e.g. "J. K. Rowling" now becomes "J", "K", "Rowling" so if we search "Rowling" it now matches
                split = book.getBookAuthor().toLowerCase().split("[-. ,:]+");
                for(String splitTerm: split){
                    //If the string inputted matches any of the terms in the book's author name, then add that book to our return list
                    if (splitTerm.startsWith(author)) {
                        bookAuthor.add(book);
                    }
                }
            }
        }
        return bookAuthor;
    }

    /**
     * findByTitle: Finds books from our bookList by title
     * @param title
     */
    private List<IBook> findByTitle(String title){
        List<IBook> bookTitle = new ArrayList<>();
        String[] split;

        if(title != null){
            //Going through all the bookList
            for(IBook book: bookList) {
                //***CHANGED***: see findByAuthor for detail
                split = book.getBookName().toLowerCase().split("[-. ,:]+");
                for(String splitTerm: split){
                    //If the string inputted matches any of the terms in the book titles, then add the book to our return list
                    if (splitTerm.startsWith(title)) {
                        bookTitle.add(book);
                    }
                }
            }
        }
        return bookTitle;
    }
}
