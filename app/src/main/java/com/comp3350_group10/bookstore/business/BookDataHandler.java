package com.comp3350_group10.bookstore.business;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.comp3350_group10.bookstore.Exceptions.NegativeStockException;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class BookDataHandler implements IBookDataHandler {
    private IBookDatabase bookDatabase;
    public static IBook currentBook;
    private List<IBook> allBooks = null;

    public BookDataHandler(){
        bookDatabase = Service.setupBookDatabase();
    }

    //Dependency injection for testing
    public BookDataHandler(IBookDatabase bookDatabase){
        this.bookDatabase = bookDatabase;
    }

    //Takes the keyword and search database with it
    //Returns result after removing duplicated results, and sorted by relevance
    /*
     * param keyword
     * return list of found books
     * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<IBook> findBooks(String keyword, boolean asc, String searchBy) {
        List<String> wordList = splitWords(keyword); //splits keywords

        //bookDatabase.getBooks() is slow, so only do it the first time.
        if (allBooks == null) allBooks = bookDatabase.getBooks();
        List<IBook> bookList = new ArrayList<>(); //stores search result

        //search database with each keyword and combining the lists
        for (IBook book : allBooks) {
            boolean matches = true;
            String title = book.getBookName().toLowerCase();
            String author = book.getBookAuthor().toLowerCase();
            String genre = book.getGenre().toLowerCase();

            for (String word : wordList) {
                word = word.toLowerCase();

                //rejects books that are not exact match
                if (!title.contains(word) && !author.contains(word) && !genre.contains(word)) {
                    matches = false;
                    break;
                }
            }

            if (matches) bookList.add(book);
        }

        //sort the booklist by the appropriate term
        if (searchBy.contains("Title")) sortTitleHelper(bookList);
        else if (searchBy.contains("Author")) sortAuthorHelper(bookList);
        if (searchBy.contains("Genre")) sortGenreHelper(bookList);

        //reverse order if asc=false
        if (!asc) Collections.reverse(bookList);

        return bookList;
    }

    //function to set the target book to the given price
    public void setPrice(IBook target, int price) {
        //only change price if price is positive
        if(price>=0) {
            target.setPrice(price);
            bookDatabase.updateBook(target);
        }
    }

    //function set the stock for the target book with the given quantity
    public void setStock(IBook target, int quantity) {
        //stock cannot be negative
        if(quantity >= 0) {
            target.setStock(quantity);
        }
        bookDatabase.updateBook(target);
    }


    //function to increment the stock by 1
    public void incrementStock(IBook target) {
        setStock(target, target.getStock() + 1);
    }


    //function to decrement the stock by 1
    public void decrementStock(IBook target) throws NegativeStockException {
        //only decrease if stock does not go below 0
        if(target.getStock() > 0)
            setStock(target, target.getStock() - 1);

        //throw exception and show popup if it goes below
        else
            throw new NegativeStockException("Stock cannot be less than 0.");
    }



    //Takes a list of books with duplication, the more duplicated the book the
    //Sort the given list of books by how many words in its title matches with the given word list
    //And gets rid of the duplicated elements
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private List<IBook> sortByRelevancy(List<IBook> bookList){
//        //relevancy is determined by # of times the book appeared in search result
//        //<Key : Value> = <IBook book : Integer relevancy>
//        HashMap<IBook,Integer> map = new HashMap<>();
//        for (IBook book : bookList) {
//            if (!map.containsKey(book)) {
//                map.put(book, 1);
//            }
//            else{
//                map.put(book, map.get(book)+1);
//            }
//        }
//
//        //descending sort by relevancy
//        List<Entry<IBook, Integer>> sortedBookList = new ArrayList<>(map.entrySet());
//        sortedBookList.sort(Entry.<IBook, Integer>comparingByValue().reversed());
//
//        //copies the sorted list to list of books to return
//        List<IBook> result = new ArrayList<>();
//        for(Entry<IBook, Integer> entries: sortedBookList){
//            result.add(entries.getKey());
//        }
//
//        return result;
//    }


    // splits the given string, ignores non-ascii words
    private List<String> splitWords(String words){
        //split input
        String[] split = words.toLowerCase().split("[-. ,:]+");

        //initialize returning list
        List<String> result = new ArrayList<>();

        //ignore non-ascii and common words
        for(String word:split) {
            if(word.matches("\\A\\p{ASCII}*\\z")){
                result.add(word);
            }
        }

        return result;
    }


    private void sortTitleHelper(List<IBook> bookList) {

        Collections.sort(bookList, (o1, o2) -> o1.getBookName().compareTo(o2.getBookName()));
    }

    private void sortAuthorHelper(List<IBook> bookList) {

        Collections.sort(bookList, (o1, o2) -> o1.getBookAuthor().compareTo(o2.getBookAuthor()));
    }

    private void sortGenreHelper(List<IBook> bookList) {

        Collections.sort(bookList, (o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
    }
}
