package com.comp3350_group10.bookstore.business.Data_Handler;

import android.os.Build;

import androidx.annotation.RequiresApi;


import com.comp3350_group10.bookstore.application.Services;
import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IUserDatabase;
import com.comp3350_group10.bookstore.persistence.hsqldb.BookDatabase;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;
import com.comp3350_group10.bookstore.objects.User;
import com.comp3350_group10.bookstore.persistence.hsqldb.UserDatabase;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class BookDataHandler implements IBookDataHandler {
    public static IBook currentBook;
    private IBookDatabase bookDatabase;
    private List<IBook> books;

    public BookDataHandler(){
        bookDatabase = Services.getBookPersistance();
        currentBook = null;
        books = null;
    }

    public BookDataHandler(IBookDatabase accessingBooks){
        this();
        this.bookDatabase = accessingBooks;
    }

    //Takes the keyword and search database with it
    //Returns result after removing duplicated results, and sorted by relevance
    /*
     * param keyword
     * return list of found books
     * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<IBook> findBooks(String keyword) throws ClassNotFoundException {

        books = bookDatabase.findBook(keyword);
        return (List<IBook>) Collections.unmodifiableCollection(books);
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
}
