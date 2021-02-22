package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.data.Book;

import java.util.List;


public interface Data {

    //function that will find and return a list of books
    // based on what user searched(title/author/ISBN)
    public List<Book> findBooks(String keyword);

    //function to change the price of a particular book
    void setPrice(Book target, float price);

    //function to increment the price of a particular book by 1
    void incrementPrice(Book target);

    //function to decrement the price of a particular book by 1
    void decrementPrice(Book target);

    //function to increment the stock of a particular book by 1
    void incrementStock(Book target);

    //function to decrement the stock of a particular book by 1
    void decrementStock(Book target);

    //function to change the quantity of stock available for
    // a particular book
    void setStock(Book target, int quantity);

    //function to check whether the current user is a manager or employee
    boolean isCurrentUserManager();

    //function to logout the current user
    void logOut();
}
