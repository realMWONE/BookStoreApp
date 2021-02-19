package com.comp3350_group10.bookstore.data;

import java.util.LinkedList;

public class BookDatabase {
    LinkedList<Object> bookList = new LinkedList<Object>();

    public BookDatabase(String isbn, String bookName, String bookAuthor, int price){
        isbn = "";
        bookName = "";
        bookAuthor = "";
        price = 0;
    }

    //createDatabase method: Creates a linkedlist of objects containing the books and their respective information
    public void createDatabase(){
        bookList.add(new BookDatabase("6783903121501", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 26));
        bookList.add(new BookDatabase("6783903121502", "Harry Potter and the Chamber of secrets", "J.K. Rowling", 26));
        bookList.add(new BookDatabase("6783903121503", "The Da Vinco Code", "Dan Brown", 30));
        bookList.add(new BookDatabase("6783903121504", "Angels and Demons", "Dan Brown", 30));
    }

    //findBook method: Finds the books by their respective ISBN
//    public Object findBook(String isbn) {
//
//    }
}



