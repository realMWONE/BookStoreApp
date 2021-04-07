/**
 * Fake Book Database
 */
package com.comp3350_group10.bookstore.persistence.fakeDB;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.R;


import java.util.ArrayList;
import java.util.List;

public class FakeBookDatabase implements IBookDatabase {
    //List for our book database which would store the list of Book objects
    final private List<IBook> bookList= new ArrayList<>();;

    //Constructor
    public FakeBookDatabase() {
        //Calling createDatabase method here so every time the BookDatabase object is created it would have all the data loaded into it
        createDatabase();
    }

    /**
     * createDatabase: Populates the database by adding new book objects with information about the books into the list
     */
    private void createDatabase(){
        bookList.add(new Book("Harry Potter and the Philosopher's Stone","5648304756357",12,2630,"26 June 1997","J.K.Rowling","Novel",2,R.drawable.philosophers_stone));
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
        bookList.add(new Book("Breaking Dawn","2051512546452",  7, 2230, "20 April 2020","Stephenie Meyer", "Novel", 1,R.drawable.breaking_dawn));
        bookList.add(new Book("Midnight Sun","2510018982862", 3, 2230, "20 April 2020", "Stephenie Meyer", "Novel", 1,  R.drawable.midnightsun));
        bookList.add(new Book("The Lord of The Rings, The Fellowship of the Ring","2500186257772", 5, 2030, "20 April 2020","J. R. R. Tolkien",  "Novel" ,89, R.drawable.lotr));
        bookList.add(new Book("The Lord of The Rings, The Two Towers","2500114562233", 12, 2030,  "20 April 2020", "J. R. R. Tolkien", "Novel" , 64, R.drawable.the_two_towers));
        bookList.add(new Book("The Book of Lost Tales","2500885265433",13, 2030,"20 April 2020", "J. R. R. Tolkien", "Novel" , 420, R.drawable.the_book_of_lost_tails));
        bookList.add(new Book("The Children of Húrin","2369852102742", 2, 2030, "20 April 2020" ,"J. R. R. Tolkien", "Novel", 69,R.drawable.the_children_of_hurin));

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
<<<<<<< HEAD
    public List<IBook> getBooks()  {
=======
    public List<IBook> getBooks(){
>>>>>>> 7935627b83d2efb2d33cec8cf8570a4a8b27dc70
        return bookList;
    }

    @Override
<<<<<<< HEAD
    public IBook insertBook(IBook book)  {
=======
    public IBook insertBook(IBook book){
>>>>>>> 7935627b83d2efb2d33cec8cf8570a4a8b27dc70
        if(!bookList.contains(book))
        {
            bookList.add(book);
        }
        return book;
    }

    @Override
<<<<<<< HEAD
    public IBook updateBook(IBook book)  {
=======
    public IBook updateBook(IBook book){
>>>>>>> 7935627b83d2efb2d33cec8cf8570a4a8b27dc70
        if (bookList.contains(book)) {
            bookList.add(bookList.indexOf(book), book);
        }
        return book;
    }

    @Override
<<<<<<< HEAD
    public void deleteBook(IBook book)  {
=======
    public void deleteBook(IBook book){
>>>>>>> 7935627b83d2efb2d33cec8cf8570a4a8b27dc70
        bookList.remove(book);
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
