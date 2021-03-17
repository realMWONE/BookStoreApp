 /**
 * HSQLDB Book Database
 */

 package com.comp3350_group10.bookstore.persistence;

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

	public BookDatabase(final String dbPath){
		this.dbPath = dbPath;
	}

	private Connection connection() throws SQLException{
		return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath+ ";shutdown=true", "SA", "");
	}

	private Book createBook(final ResultSet rs) throws SQLException{
		final String bookName = rs.getString("bookName");
		final String isbn = rs.getString("isbn");
		final int quantity = rs.getInt("quantity");
		final int price = rs.getInt("price");
		final String date = rs.getString("date");
		final String author = rs.getString("author");
		final String genre = rs.getString("genre");
		final int reserve = rs.getInt("reserve");

		return new Book(bookName, isbn, quantity, price, date, author, genre, reserve, 0);
	}

	@Override
	public List<Book> findBooks(){
		final List<Book> books = new ArrayList<>();

		try (final Connection conn = connection()){
			final Statement stmt = conn.createStatement();
			final ResultSet rtst = stmt.executeQuery("SELECT * FROM BOOKS");

			while(rtst.next()){
				final Book book = createBook(rtst);
			}

			rtst.close();
			stmt.close();
		}

		catch(final SQLException e){
			throw new PersistenceException(e);
		}
		return books;
	}

	@Override
	public Book insertBook(Book book) {

		try(final Connection conn = connection()) {
			final PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BOOKS VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookIsbn());
			pstmt.setInt(3, book.getStock());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getDate());
			pstmt.setString(6, book.getBookAuthor());
			pstmt.setString(7, book.getGenre());
			pstmt.setInt(8, book.getReserve());
			pstmt.executeUpdate();

			return book;
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}

	@Override
	public Book updateBook(Book book){

		try (final Connection conn = connection()){
			final PreparedStatement pstmt = conn.prepareStatement("UPDATE BOOKS SET quantity=?,price=?, reserve=?");
			pstmt.setInt(1, book.getStock());
			pstmt.setInt(2, book.getPrice());
			pstmt.setInt(3, book.getReserve());

			return book;
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}

	@Override
	public void deleteBook(Book book){
		try(final Connection conn = connection()){
			final PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BOOKS WHERE isbn=?");
			pstmt.setString(1, book.getBookIsbn());
			pstmt.executeUpdate();
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}
}