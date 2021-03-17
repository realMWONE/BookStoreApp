package com.comp3350_group10.bookstore.persistence;

/**
 * Interface for Book
 */

public interface IBook {
    //Getters and Setters of Book.java
    String getBookName();
    String getBookAuthor();
    String getDate();
    int getPrice();
    String getBookIsbn();
    int getStock();
    int getImage();
    int getReserve();
    String getGenre();
}