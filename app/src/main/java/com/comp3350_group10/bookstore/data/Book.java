/**
 * Book Class for Book Database
 */
package com.comp3350_group10.bookstore.data;
public class Book {

    //private variables for Book
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private int price;
    private int stockAmount;

    /**
     * Book Constructor: Initializes the books
     * @param isbn
     * @param bookName
     * @param bookAuthor
     * @param price
     * @param stockAmount
     */
    public Book(String isbn, String bookName, String bookAuthor, int price, int stockAmount) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.stockAmount = stockAmount;
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
    public int getStockAmount(){
        return stockAmount;
    }

    //setPrice: Sets the price of the book to new price.
    public void setPrice(int price){
        this.price = price;
    }

    //setStockAmount: Sets the amount of books in stock to a new stock amount
    public void setStockAmount(int stockAmount){
        this.stockAmount = stockAmount;
    }
}

