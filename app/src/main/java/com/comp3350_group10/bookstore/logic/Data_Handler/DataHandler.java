package com.comp3350_group10.bookstore.logic.Data_Handler;
import java.util.*;
import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.Book;
import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.data.BookDatabase;
import com.comp3350_group10.bookstore.data.IBookDatabase;
import com.comp3350_group10.bookstore.data.User;

import java.util.List;

public class DataHandler implements Data{

    private User currentUser;
    private IBookDatabase bookDatabase = new BookDatabase();

    //Takes the keyword and search databsse with it
    //Will separate the keyword by spaces and dots, as well as making the keywords lower case
    /*
    * param keyword
    * return list of found books
    * */
    public List<IBook> findBooks(String keyword){
        return bookDatabase.findBook(keyword.toLowerCase().split("[-. ,]+"));
    }


    public void setPrice(Book target, float price){
        //make sure target is initialized
        if(target!=null){
            //price cannot be negative
            assert price >= 0 : "The price cannot be set to negative number";
            target.setPrice(price);
        }
    }

    public void incrementPrice(IBook target) {
        setPrice(target, target.getPrice()+1);
    }

    public void decrementPrice(IBook target){
        setPrice(target, target.getPrice()-1);
    }

    public void setStock(IBook target, int quantity) {
        //make sure target is initialized
        if (target != null) {
            //price cannot be negative
            assert quantity >= 0 : "The price cannot be set to negative number";
            target.setStock(quantity);
        }
    }

    public void incrementStock(IBook target) {
        //make sure target is initialized
        setStock(target, target.getStock()+1);
        }

    public void decrementStock(IBook target){
        //Make sure target is initialized and do not decrease if stock is less than 0
        setStock(target, target.getStock()-1);
    }

    public boolean isCurrentUserManager(){
        return (currentUser.getUserType() == UserType.Manager);
    }

    public void logOut(){
        assert currentUser != null : "Not logged in";
        currentUser = null;
    }

}
