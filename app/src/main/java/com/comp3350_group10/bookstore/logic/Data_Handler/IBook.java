package com.comp3350_group10.bookstore.logic.Data_Handler;

public interface IBook
{
    String getBookTitle();
    String getAuthor();
    String getBookISBN();
    int getStock();
    void adjustStock(int amount);
    void setStock(int amount);
    int getBookPrice();
    void setBookPrice(int amount);
}
