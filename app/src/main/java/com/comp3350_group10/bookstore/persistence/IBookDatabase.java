/**
 * Interface for BookDatabase
 */

package com.comp3350_group10.bookstore.persistence;

import java.util.List;

public interface IBookDatabase
{
    List<IBook> findBook(String searchTerm) throws ClassNotFoundException;
    List<IBook> getBooks() throws ClassNotFoundException;
    IBook insertBook(IBook book) throws ClassNotFoundException;
    void updateBook(IBook book) throws ClassNotFoundException;
    void deleteBook(IBook book) throws ClassNotFoundException;
}
