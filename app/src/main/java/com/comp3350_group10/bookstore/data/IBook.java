package com.comp3350_group10.bookstore.data;

/**
 * Interface for Book
 */

public interface IBook{
    //Getters and Setters of Book.java
    String getBookName();
    String getBookAuthor();
    int getPrice();
    String getBookIsbn();
    int getStockAmount();
    void setPrice(int price);
    void setStockAmount(int stockAmount);
}
