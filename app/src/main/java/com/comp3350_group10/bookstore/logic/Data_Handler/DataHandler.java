package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.data.BookDatabase;
import com.comp3350_group10.bookstore.data.IBookDatabase;
import com.comp3350_group10.bookstore.data.User;

import java.util.ArrayList;
import java.util.List;

public class DataHandler implements IDataHandler {

    private User currentUser;
    private IBookDatabase bookDatabase = new BookDatabase();
    public static IBook currentBook;

    //Takes the keyword and search database with it
    //Will separate the keyword by spaces and dots, as well as making the keywords lower case
    /*
    * param keyword
    * return list of found books
    * */
    public List<IBook> findBooks(String keyword){
        List<IBook> bookList = new ArrayList<>();
        List<IBook> result = new ArrayList<>();
        String[] wordList = keyword.toLowerCase().split("[-. ,]+");

        for(String word: wordList){
            bookList.addAll(bookDatabase.findBook(word));
        }

        //remove duplicate
        for (IBook book : bookList) {
            if (!result.contains(book)) {
                result.add(book);
            }
        }


        return result;
    }


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
        }

        catch(NullPointerException e)
        {
            System.out.println(e+"caught in DataHandler.java method - setPrice()");
        }
    }

    public void incrementPrice(IBook target) {
        try {
            setPrice(target, target.getPrice() + 1);
        } catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - incrementPrice()");
        }
    }

    public void decrementPrice(IBook target){
        try{
            setPrice(target, target.getPrice()-1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - decrementPrice()");
        }

    }

    public void setStock(IBook target, int quantity){
        //make sure target is initialized
        try{
            //stock cannot be negative
            if(quantity > 0){
                target.setStock(quantity);
            }
            else{
                System.out.println("The stock cannot be set to negative number");
            }
        }
        catch(NullPointerException e)
        {
            System.out.println(e+"caught in DataHandler.java method - setStock()");
        }
    }

    public void incrementStock(IBook target) {
        //make sure target is initialized
        try {
            setStock(target, target.getStock() + 1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - incrementStock()");
        }
        }

    public void decrementStock(IBook target){
        //Make sure target is initialized and do not decrease if stock is less than 0
        try {
            setStock(target, target.getStock() - 1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - decrementStock()");
        }
    }

    public boolean isCurrentUserManager(){
        return (currentUser.getUserType() == UserType.Manager);
    }

    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

}
