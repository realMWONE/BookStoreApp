package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.data.BookDatabase;
import com.comp3350_group10.bookstore.data.IBookDatabase;
import com.comp3350_group10.bookstore.data.User;

import java.util.ArrayList;
import java.util.List;

public class DataHandler implements IDataHandler {

    public static User currentUser = null;
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
        }

        catch(NullPointerException e)
        {
            System.out.println(e+"caught in DataHandler.java method - setPrice()");
        }
    }


    //function to increment the price by 1
    public void incrementPrice(IBook target) {
        try {
            setPrice(target, target.getPrice() + 1);
        } catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - incrementPrice()");
        }
    }

    //function to decrement price by 1
    public void decrementPrice(IBook target){
        try{
            setPrice(target, target.getPrice()-1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - decrementPrice()");
        }

    }


    //function set the stock for the target book with the given quantity
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


    //function to increment the stock by 1
    public void incrementStock(IBook target) {
        //make sure target is initialized
        try {
            setStock(target, target.getStock() + 1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - incrementStock()");
        }
    }


    //function to decrement the stock by 1
    public void decrementStock(IBook target){
        //Make sure target is initialized and do not decrease if stock is less than 0
        try {
            setStock(target, target.getStock() - 1);
        }
        catch (NullPointerException e) {
            System.out.println(e + "caught in DataHandler.java method - decrementStock()");
        }
    }


    //function to check whether the current user is a manager or employee
    public boolean isCurrentUserManager(){
        return (currentUser.getUserType() == UserType.Manager);
    }


    //function to logout the current user
    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }


    public void changePassword(String oldPw, String newPw, String confirmNewPw){}

}
