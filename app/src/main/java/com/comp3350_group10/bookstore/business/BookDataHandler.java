package com.comp3350_group10.bookstore.business;

import android.os.Build;

import androidx.annotation.RequiresApi;


import com.comp3350_group10.bookstore.application.Main;
import com.comp3350_group10.bookstore.application.Service;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class BookDataHandler implements IBookDataHandler {
    private IBookDatabase bookDatabase;
    public static IBook currentBook;

    public BookDataHandler(){
        bookDatabase = Service.setupBookDatabase();
    }

    //Takes the keyword and search database with it
    //Returns result after removing duplicated results, and sorted by relevance
    /*
     * param keyword
     * return list of found books
     * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<IBook> findBooks(String keyword) throws ClassNotFoundException {
        List<String> wordList = splitWords(keyword); //splits keywords

        List<IBook> bookList = new ArrayList<>();   //stores search result

        //search database with each keyword and combining the lists
        for(String word: wordList){
            bookList.addAll(bookDatabase.findBook(word));
        }

        //sort the booklist by relevancy (times it appeared in search result) and remove duplication
        bookList = sortByRelevancy(bookList);

        return bookList;
    }

    //function to set the target book to the given price
    public void setPrice(IBook target, int price){
        //make sure target is initialized
        try
        {
            //price cannot be negative
            if(price>=0){
                target.setPrice(price);
            }
            else{
                System.out.println("The price cannot be set to negative number");
            }
            bookDatabase.updateBook(target);
        }

        catch(NullPointerException | ClassNotFoundException e)
        {
            System.out.println(e+"caught in UserDataHandler.java method - setPrice()");
        }
    }


    //function to increment the price by 1
    public void incrementPrice(IBook target) {
        try {
            setPrice(target, target.getPrice() + 1);
        } catch (NullPointerException e) {
            System.out.println(e + "caught in UserDataHandler.java method - incrementPrice()");
        }
    }

    //function to decrement price by 1
    public void decrementPrice(IBook target){
        try{
            setPrice(target, target.getPrice()-1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in UserDataHandler.java method - decrementPrice()");
        }

    }


    //function set the stock for the target book with the given quantity
    public void setStock(IBook target, int quantity) throws ClassNotFoundException {
        //make sure target is initialized
        try{
            //stock cannot be negative
            if(quantity >= 0){
                target.setStock(quantity);
            }
            else{
                System.out.println("The stock cannot be set to negative number");
            }
            bookDatabase.updateBook(target);
        }
        catch(NullPointerException e)
        {
            System.out.println(e+"caught in UserDataHandler.java method - setStock()");
        }
    }


    //function to increment the stock by 1
    public void incrementStock(IBook target) {
        //make sure target is initialized
        try {
            setStock(target, target.getStock() + 1);
        }
        catch (NullPointerException | ClassNotFoundException e) {
            System.out.println(e + "caught in UserDataHandler.java method - incrementStock()");
        }
    }


    //function to decrement the stock by 1
    public void decrementStock(IBook target){
        //Make sure target is initialized and do not decrease if stock is less than 0
        try {
            setStock(target, target.getStock() - 1);
        }
        catch (NullPointerException | ClassNotFoundException e) {
            System.out.println(e + "caught in DataHandler.java method - decrementStock()");
        }
    }



    //Takes a list of books with duplication, the more duplicated the book the
    //Sort the given list of books by how many words in its title matches with the given word list
    //And gets rid of the duplicated elements
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<IBook> sortByRelevancy(List<IBook> bookList){
        //relevancy is determined by # of times the book appeared in search result
        //<Key : Value> = <IBook book : Integer relevancy>
        HashMap<IBook,Integer> map = new HashMap<>();
        for (IBook book : bookList) {
            if (!map.containsKey(book)) {
                map.put(book, 1);
            }
            else{
                map.put(book, map.get(book)+1);
                //TODO: Warning:(195, 31) Unboxing of 'map.get(book)' may produce 'NullPointerException'
            }
        }

        //descending sort by relevancy
        List<Entry<IBook, Integer>> sortedBookList = new ArrayList<>(map.entrySet());
        sortedBookList.sort(Entry.<IBook, Integer>comparingByValue().reversed());

        //copies the sorted list to list of books to return
        List<IBook> result = new ArrayList<>();
        for(Entry<IBook, Integer> entries: sortedBookList){
            result.add(entries.getKey());
        }

        return result;
    }


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




    // TODO: Take care of IUserDatabase bugs after it's implemented
}
