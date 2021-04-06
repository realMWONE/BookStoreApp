/**
 * Interface for BookDatabase
 */

package com.comp3350_group10.bookstore.persistence;

import java.util.List;

public interface IBookDatabase
{
    List<IBook> findBook(String searchTerm);
    List<IBook> getBooks();
    IBook insertBook(IBook book);
    IBook updateBook(IBook book);
    void deleteBook(IBook book);
}
