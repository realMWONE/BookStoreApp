package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.UserType;
import com.comp3350_group10.bookstore.data.Book;
import com.comp3350_group10.bookstore.data.BookDatabase;
import com.comp3350_group10.bookstore.data.User;

import java.util.List;

public class DataHandler implements Data{

    private User currentUser;

    //public List<Book> findBooks(String keyword){
    //    return BookDatabase.findBooks(keyword);
    //}

    public void setPrice(Book target, int price){
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

    public void incrementPrice(Book target) {
        setPrice(target, target.getPrice()+1);
    }

    public void decrementPrice(Book target){
        setPrice(target, target.getPrice()-1);
    }


    public void setStock(Book target, int quantity){
        //make sure target is initialized
        if(target!=null){
            //stock cannot be negative
            if(quantity > 0){
                target.setStockAmount(quantity);
            }
            else{
                System.out.println("The stock cannot be set to negative number");
            }
        }
    }


    public void incrementStock(Book target) {
        //make sure target is initialized
        setStock(target, target.getStockAmount()+1);
        }

    public void decrementStock(Book target){
        //Make sure target is initialized and do not decrease if stock is less than 0
        setStock(target, target.getStockAmount()-1);
    }

    public boolean isCurrentUserManager(){
        return (currentUser.getUserType() == UserType.Manager);
    }

    public void logOut(){
        if(currentUser!=null)
            currentUser = null;
    }

}
