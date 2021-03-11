/**
 * Book Class for Book Database
 */
package com.comp3350_group10.bookstore.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Book implements IBook, Serializable {

    //private variables for Book
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private int price;
    private int stockAmount;
    private int imageReference;

    /**
     * Book Constructor: Initializes the books
     * @param isbn
     * @param bookName
     * @param bookAuthor
     * @param price
     * @param stockAmount
     */
    public Book(String isbn, String bookName, String bookAuthor, int price, int stockAmount, int imageReference) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.stockAmount = stockAmount;
        this.imageReference = imageReference;
    }

    //getBookName: Returns the Book Name
    public String getBookName(){
        return bookName;
    }

    //getBookAuthor: Returns the Book Author
    public String getBookAuthor(){
        return bookAuthor;
    }

    //getPrice: Returns the Book Price
    public int getPrice(){
        return price;
    }

    //getBookIsbn: Returns the Book ISBN
    public String getBookIsbn(){
        return isbn;
    }

    //getStockAmount: Returns the stock amount
    public int getStock(){
        return stockAmount;
    }

    @Override
    public int getImage() {
        return 0;
    }

    //setPrice: Sets the price of the book to new price.
    public void setPrice(int price){
        this.price = price;
    }

    //setStockAmount: Sets the amount of books in stock to a new stock amount
    public void setStock(int stockAmount){
        this.stockAmount = stockAmount;
    }

    //imageReference: Gets the image reference
    public int getImageReference(){
        return imageReference;
    }
}
