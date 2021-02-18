package com.comp3350_group10.bookstore.logic.Data_Handler;

import com.comp3350_group10.bookstore.data.Book;


public interface DataHandler {

    Book[] findBooks(keyword);

    void incrementStock(Book target);

    void decrementStock(Book target);

    void setStock(Book target, int quantity);

    boolean isCurrentUserManager();

    void logOut();
}
