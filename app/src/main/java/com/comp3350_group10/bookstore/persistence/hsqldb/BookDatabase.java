package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDatabase implements IBookDatabase
{
    Connection connection;

    public BookDatabase()
    {
        CreateConnection();
        AddTable();
        AddBook("Harry Potter", "J. K. Rowling", "123456789");
        AddBook("Harry Potter", "J. K. Rowling", "23456789");
        AddBook("Harry Potter", "J. K. Rowling", "13456789");
        AddBook("Harry Potter", "J. K. Rowling", "12456789");
        AddBook("Harry Potter", "J. K. Rowling", "12356789");
        AddBook("Harry Potter", "J. K. Rowling", "12346789");
        Print();
    }

    private void CreateConnection()
    {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb/demodb", "SA", "");

            if (connection == null) System.out.println("Database failed");
            else System.out.println("Database connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void AddTable()
    {
        String books = "create table books (" +
                "title varchar(20)," +
                "author varchar(20), " +
                "isbn varchar(13)," +
                "primary key(isbn));";

        try {
            connection.createStatement().executeUpdate(books);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void AddBook(String title, String author, String isbn)
    {
        String book = "insert into books (title, author, isbn) values ('" + title + "', '" + author + "', '" + isbn + "');";

        try {
            connection.createStatement().executeUpdate(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Print()
    {
        PreparedStatement p = null;

        try {
            p = connection.prepareStatement("select * from books;");
            ResultSet resultSet = p.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");

                System.out.println(title + " written by " + author + " with an ISBN of " + isbn);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<IBook> findBook(String searchTerm) throws ClassNotFoundException {
        return null;
    }

    @Override
    public List<IBook> getBooks() throws ClassNotFoundException {
        return null;
    }

    @Override
    public IBook insertBook(IBook book) throws ClassNotFoundException {
        return null;
    }

    @Override
    public void updateBook(IBook book) throws ClassNotFoundException {

    }

    @Override
    public void deleteBook(IBook book) throws ClassNotFoundException {

    }
}
