package com.comp3350_group10.bookstore.data;

/**
 * Interface for BookDatabase
 */

import java.util.List;

public interface IBookDatabase
{
    List<Book> findBook(String searchTerm);
}
