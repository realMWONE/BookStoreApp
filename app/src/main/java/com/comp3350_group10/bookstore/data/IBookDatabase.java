package com.comp3350_group10.bookstore.data;

import java.util.List;

public interface IBookDatabase
{
    Book getBook(String ISBN);
    List<Book> findBooks(String searchTerm);
}
