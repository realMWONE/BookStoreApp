package com.comp3350_group10.bookstore.persistence;

import android.os.Parcelable;

/**
 * Interface for Book
 */

public interface IBook {
    //Getters and Setters of Book.java
    String getBookName();
    String getBookAuthor();
    int getPrice();
    String getBookIsbn();
    void setPrice(int price);
    void setStock(int quantity);
    int getStock();
    int getImage();
}
