package com.comp3350_group10.bookstore.data;

import java.util.List;

public interface IBookDatabase
{
    String findBook(String ISBN);
    List<Book> findBooksByAuthor(String searchTerm);
}
