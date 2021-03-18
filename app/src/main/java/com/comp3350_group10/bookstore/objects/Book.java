/**
 * Book Class for Book Database
 */

package com.comp3350_group10.bookstore.objects;
import com.comp3350_group10.bookstore.persistence.IBook;

import java.io.Serializable;

public class Book implements IBook, Serializable {

    //private variables for Book
    private String isbn;
    private String bookName;
    private String author;
    private int price;
    private int stockAmount;
    private int imageReference;
    private String date;
    private String genre;
    private int reserve;

    /**
     * Book constructor: Initializes the book
     * @param author
     * @param isbn
     * @param stockAmount
     * @param price
     * @param date
     * @param author
     * @param genre
     * @param reserve
     */
    public Book(String bookName, String isbn, int stockAmount, int price, String date, String author, String genre, int reserve, int imageReference) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.stockAmount = stockAmount;
        this.date = date;
        this.genre = genre;
        this.reserve = reserve;
        this.imageReference = imageReference;
    }

    //getBookName: Returns the Book Name
    public String getBookName(){
        return bookName;
    }

    //getBookAuthor: Returns the Book Author
    public String getBookAuthor(){
        return author;
    }

    //getDate: Returns the date when the book was published
    public String getDate(){
        return date;
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

    //getReserve: Returns the amount of users who have reserved this book
    public int getReserve(){
        return reserve;
    }

    //getGenre: Returns the book genre
    public String getGenre(){
        return genre;
    }

    //imageReference: Gets the image reference
    public int getImage(){
        return imageReference;
    }

    //setPrice: Sets the price of the book to new price.
    public void setPrice(int price){ this.price = price; }

    //setStockAmount: Sets the amount of books in stock to a new stock amount
    public void setStock(int stockAmount){ this.stockAmount = stockAmount; }

}
