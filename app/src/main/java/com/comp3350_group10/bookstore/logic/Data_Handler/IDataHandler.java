package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.data.IBook;
import com.comp3350_group10.bookstore.data.User;

import java.util.List;


public interface IDataHandler {

    //function that will find and return a list of books
    // based on what user searched(title/author/ISBN)
    List<IBook> findBooks(String keyword);

    //function to change the price of a particular book
    void setPrice(IBook target, int price);

    //function to increment the price of a particular book by 1
    void incrementPrice(IBook target);

    //function to decrement the price of a particular book by 1
    void decrementPrice(IBook target);

    //function to increment the stock of a particular book by 1
    void incrementStock(IBook target);

    //function to decrement the stock of a particular book by 1
    void decrementStock(IBook target);

    //function to change the quantity of stock available for
    // a particular book
    void setStock(IBook target, int quantity);

    //function to check whether the current user is a manager or employee
    boolean isCurrentUserManager();

    //function to logout the current user
    void logOut();

<<<<<<< HEAD
    //function to changePassword for the logged in user
=======
>>>>>>> c9d6e0208dec5e6929048b82145d663bf8248a87
    void changePassword(String oldPw, String newPw, String confirmNewPw);
}
