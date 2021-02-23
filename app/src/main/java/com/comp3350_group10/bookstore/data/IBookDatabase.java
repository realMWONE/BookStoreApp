package com.comp3350_group10.bookstore.data;

import java.util.List;

public interface IBookDatabase
{
    List<Book> findBook(String searchTerm);
}
