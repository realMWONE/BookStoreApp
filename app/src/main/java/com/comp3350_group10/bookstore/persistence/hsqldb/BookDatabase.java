/**
 * HSQLDB Book Database
 */

package com.comp3350_group10.bookstore.persistence.hsqldb;

import com.comp3350_group10.bookstore.objects.Book;
import com.comp3350_group10.bookstore.persistence.IBook;
import com.comp3350_group10.bookstore.persistence.IBookDatabase;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;


public class BookDatabase implements IBookDatabase {

    private final String dbPath;
    private List<IBook> bookList;

    public BookDatabase(String dbPath) {
        this.dbPath = dbPath;
        bookList = getBooks();
    }

    /*public BookDatabase(){
        IBook book1 = new Book("Diary of Wimpy Kid: The Getaway","5987450215825",13,1250,"16 January 2005","Jeff Kinney","Comedy",4, 700031);
        IBook book2 = new Book("Diary of Wimpy Kid: Double Down","4578932145250",12,1280,"21 February 2006","Jeff Kinney","Comedy",3,700003);
        List<IBook> list = new ArrayList<IBook>();
        list.add(book1);
        list.add(book2);
        this.bookList=list;
        for(int i=0;i<this.bookList.size();i++){
            System.out.println("name = "+bookList.get(i).getBookName());
        }
        this.dbPath="";
    }*/

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }


    private Book createBook(ResultSet rs) throws SQLException{
         String bookName = rs.getString("bookName");
         String isbn = rs.getString("isbn");
         int quantity = rs.getInt("quantity");
         int price = rs.getInt("price");
         String date = rs.getString("date");
         String author = rs.getString("author");
         String genre = rs.getString("genre");
         int reserve = rs.getInt("reserve");
         int imageReference = rs.getInt("image");
        return new Book(bookName, isbn, quantity, price, date, author, genre, reserve, imageReference);
    }


    /**
     * findBooks: Finds and returns the book objects based on their book ISBN
     * @param searchTerm
     * @return
     */
    public List<IBook> findBook(String searchTerm) {

        //Lists which contains book objects related to specific search terms
        List<IBook> findByISBN  = findByISBN(searchTerm);
        List<IBook> findByAuthor = findByAuthor(searchTerm);
        List<IBook> findByTitle = findByTitle(searchTerm);

        //Filtering by removing duplicates and adding them all into a single list which has elements of the search term
        List<IBook> bookResult = new ArrayList<>();

        for(IBook book: findByISBN){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(IBook book: findByAuthor){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }
        for(IBook book: findByTitle){
            if(!bookResult.contains(book)){
                bookResult.add(book);
            }
        }

        return bookResult;
    }

    /**
     * findByISBN: Finds books from our bookList by ISBN
     * @param isbn
     */
    private List<IBook> findByISBN(String isbn) {
        List<IBook> bookIsbn = new ArrayList<>();
        if(isbn != null && bookList != null){
            //Going through all the bookList
            for(IBook book: bookList){
                //If the string inputted matches any of the strings in the our bookList, then add that to our local list
                if(book.getBookIsbn().startsWith(isbn)||book.getBookIsbn().equals(isbn)){
                    bookIsbn.add(book);
                }
            }
        }
        return bookIsbn;
    }

    /**
     * findByAuthor: Finds books from our bookList by author
     * @param author
     */
    private List<IBook> findByAuthor(String author) {
        System.out.println("Author = "+author);
        List<IBook> bookAuthor = new ArrayList<>();
        String[] split;
        if(author != null){
            //Going through all the bookList
            for(IBook book: bookList) {
                //***CHANGED***:
                // now instead of pulling whole string and try to match with the search term,
                // we split the term by delimeters and try to match each search term with each data term
                //e.g. "J. K. Rowling" now becomes "J", "K", "Rowling" so if we search "Rowling" it now matches
                split = book.getBookAuthor().toLowerCase().split("[-. ,:]+");
                for(String splitTerm: split){
                    //If the string inputted matches any of the terms in the book's author name, then add that book to our return list
                    if (splitTerm.startsWith(author)||splitTerm.equals(author.toLowerCase())) {
                        bookAuthor.add(book);
                    }
                }
            }
        }
        return bookAuthor;
    }

    /**
     * findByTitle: Finds books from our bookList by title
     * @param title
     */
    private List<IBook> findByTitle(String title) {
        List<IBook> bookTitle = new ArrayList<>();
        String[] split;

        if(title != null){
            //Going through all the bookList
            for(IBook book: bookList) {
                //***CHANGED***: see findByAuthor for detail
                split = book.getBookName().toLowerCase().split("[-. ,:]+");
                for(String splitTerm: split){
                    //If the string inputted matches any of the terms in the book titles, then add the book to our return list
                    if (splitTerm.startsWith(title)||splitTerm.equals(title.toLowerCase())) {
                        bookTitle.add(book);
                    }
                }
            }
        }
        return bookTitle;
    }


    @Override
    public List<IBook> getBooks() {

        List<IBook> books = new ArrayList<>();
        try(Connection c = connection()) {
            Statement stmt = c.createStatement();
            ResultSet rtst = stmt.executeQuery("SELECT * FROM books");

            while(rtst.next()){
                Book book = createBook(rtst);
                books.add(book);
            }
            rtst.close();
            stmt.close();
            return books;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public IBook insertBook(IBook book) {
        try(Connection c = connection()) {
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookIsbn());
            pstmt.setInt(3, book.getStock());
            pstmt.setInt(4, book.getPrice());
            pstmt.setString(5, book.getDate());
            pstmt.setString(6, book.getBookAuthor());
            pstmt.setString(7, book.getGenre());
            pstmt.setInt(8, book.getReserve());
            pstmt.setInt(9, book.getImage());
            pstmt.executeUpdate();

            //have to update the bookList
            this.bookList =getBooks();
            return book;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public IBook updateBook(IBook book) {

        try (Connection c = connection()){
            PreparedStatement pstmt = c.prepareStatement("UPDATE books SET quantity=?,price=?,reserve=? WHERE isbn = ?");
            pstmt.setInt(1, book.getStock());
            pstmt.setInt(2, book.getPrice());
            pstmt.setInt(3, book.getReserve());
            pstmt.setString(4, book.getBookIsbn());
            pstmt.executeUpdate();
            //update the bookList
            this.bookList=getBooks();
            return book;
        }
        catch(SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteBook(IBook book) {
        try( Connection c = connection()){
            PreparedStatement pstmt = c.prepareStatement("DELETE FROM books WHERE isbn=?");
            pstmt.setString(1, book.getBookIsbn());
            //update the bookList
            this.bookList = getBooks();
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            throw new PersistenceException(e);
        }
    }
}