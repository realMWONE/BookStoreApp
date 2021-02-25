package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.data.BookDatabase;
import com.comp3350_group10.bookstore.data.IBookDatabase;
import com.comp3350_group10.bookstore.data.User;

import java.util.List;

public class DataHandler implements Data{

    private User currentUser;
    private IBookDatabase bookDatabase = new BookDatabase();
    public static IBook currentBook;

    public List<IBook> findBooks(String keyword){

        return bookDatabase.findBook(keyword);
    }

    public void setPrice(IBook target, int price){
        //make sure target is initialized
        if(target!=null){
            //price cannot be negative
            if(price>=0){
                target.setPrice(price);
            }
            else{
                System.out.println("The price cannot be set to negative number");
            }
        }
    }

    public void incrementPrice(IBook target) {
        setPrice(target, target.getPrice()+1);
    }

    public void decrementPrice(IBook target){
        setPrice(target, target.getPrice()-1);
    }

    public void setStock(IBook target, int quantity){
        //make sure target is initialized
        if(target!=null){
            //stock cannot be negative
            if(quantity > 0){
                target.setStock(quantity);
            }
            else{
                System.out.println("The stock cannot be set to negative number");
            }
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
        if(currentUser!=null)
            currentUser = null;
    }

}
