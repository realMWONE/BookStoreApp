/**
 * Interface for BookDatabase
 */

package com.comp3350_group10.bookstore.persistence;
import java.util.List;

public interface IBookDatabase
{
    List<Book> findBooks();
    Book insertBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Book book);
}
