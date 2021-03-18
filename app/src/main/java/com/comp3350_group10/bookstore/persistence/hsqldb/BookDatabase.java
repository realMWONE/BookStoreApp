/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.data.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookDatabase implements IBookDatabase {
    //List for our book database which would store the list of Book objects
    private List<Book> bookList;

    //Constructor
    public BookDatabase() {
        //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    private void createDatabase(){
        bookList = new ArrayList<>();
        bookList.add(new Book("3214686513501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 2630, 2, R.drawable.philosophers_stone));
        bookList.add(new Book("1100303121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 2650, 19, R.drawable.harry_potter_and_the_chamber_of_secrets));
        bookList.add(new Book("9260783121503", "The Da Vinci Code", "Dan Brown", 3000, 20, R.drawable.the_da_vinci_code));
        bookList.add(new Book("4458860121504", "Angels and Demons", "Dan Brown", 3000, 5, R.drawable.angels_demons));
        bookList.add(new Book("1288903121505", "Diary of Wimpy Kid:The Getaway", "Jeff Kinney", 2500, 15, R.drawable.diary_of_wimpy_kid_the_getaway));
        bookList.add(new Book("6481103121506", "Diary of Wimpy Kid: Double Down", "Jeff Kinney", 2500, 13, R.drawable.diary_of_wimpy_kid_double_down));
        bookList.add(new Book("6003255121507", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2630, 11, R.drawable.prisoner_of_azkaban));
        bookList.add(new Book("6783908521508", "Harry Potter and the Goblet of Fire", "J.K. Rowling", 2630, 10, R.drawable.harry_potter_and_the_goblet_fire));
        bookList.add(new Book("6588503121509", "Harry Potter and the Order of Phoenix", "J.K. Rowling", 2630, 8, R.drawable.harry_potter_and_the_order_of_the_phoenix));
        bookList.add(new Book("6654684858510", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2630, 6, R.drawable.harry_potter_and_the_half_blood_prince));
        bookList.add(new Book("6874684025221", "Harry Potter and the Deathly Hallows", "J.K. Rowling", 2630, 6, R.drawable.harry_potter_and_the_deathly_hallows));
        bookList.add(new Book("5354684656848", "Harry Potter and the Cursed Child", "J.K.Rowling",2730, 7, R.drawable.harry_potter_and_the_cursed_child));
        bookList.add(new Book("2510323255565", "Twilight", "Stephenie Meyer", 2030, 12, R.drawable.twilight));
        bookList.add(new Book("2551819816185", "Eclipse", "Stephenie Meyer", 2230, 14, R.drawable.eclipse));
        bookList.add(new Book("2516511685000", "New Moon", "Stephenie Meyer", 2130, 8, R.drawable.new_moon));
        bookList.add(new Book("2051512546452", "Breaking Dawn", "Stephenie Meyer", 2230, 7, R.drawable.breaking_dawn));
        bookList.add(new Book("2510018982862", "Midnight Sun", "Stephenie Meyer", 2230, 3, R.drawable.midnightsun));
        bookList.add(new Book("2500186257772", "The Lord of The Rings, The Fellowship of the Ring", "J. R. R. Tolkien", 2030, 5, R.drawable.lotr));
        bookList.add(new Book("2500114562233", "The Lord of The Rings, The Two Towers", "J. R. R. Tolkien", 2030, 12, R.drawable.the_two_towers));
        bookList.add(new Book("2500885265433", "The Book of Lost Tales", "J. R. R. Tolkien", 2030, 13, R.drawable.the_book_of_lost_tails));
        bookList.add(new Book("2369852102742", "The Children of Húrin", "J. R. R. Tolkien", 2030, 2, R.drawable.the_children_of_hurin));
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

    /**
     * findByISBN: Finds books from our bookList by ISBN
     * @param isbn
     */
    private List<IBook> findByISBN(String isbn){
        List<IBook> bookIsbn = new ArrayList<>();
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
    private List<IBook> findByAuthor(String author){
        List<IBook> bookAuthor = new ArrayList<>();
        if(author != null){
            //Going through all the bookList
            for(Book book: bookList){
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if(book.getBookAuthor().toLowerCase().contains(author)){
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
    private List<IBook> findByTitle(String title){
        List<IBook> bookTitle = new ArrayList<>();
        if(title != null){
            //Going through all the bookList
            for(Book book: bookList) {
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if (book.getBookName().toLowerCase().contains(title)) {
                    bookTitle.add(book);
                }
            }
        }
        return bookTitle;
    }
}





