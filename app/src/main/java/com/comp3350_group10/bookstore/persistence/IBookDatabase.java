package com.comp3350_group10.bookstore.persistence;

/**
 * Interface for BookDatabase
 */

import com.comp3350_group10.bookstore.persistence.IBook;

import java.util.List;

public interface IBookDatabase
{
    List<IBook> findBook(String searchTerm);
}
