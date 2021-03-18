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

	private List<IBook> bookList;

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
		final int imageReference = rs.getInt("image");

		return new Book(bookName, isbn, quantity, price, date, author, genre, reserve, imageReference);
	}


	/**
	 * findBooks: Finds and returns the book objects based on their book ISBN
	 * @param searchTerm
	 * @return
	 */
	public List<IBook> findBook(String searchTerm) {

		//Lists which contains book objects related to specific search terms
		List<IBook> findByISBN = findByISBN(searchTerm);
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
	private List<IBook> findByISBN(String isbn){
		List<IBook> bookIsbn = getBooks();
		if(isbn != null){
			//Going through all the bookList
			for(IBook book: bookList){
				//If the string inputted matches any of the strings in the our bookList, then add that to our local list
				if(book.getBookIsbn().startsWith(isbn)){
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
	private List<IBook> findByAuthor(String author){
		List<IBook> bookAuthor = getBooks();
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
					if (splitTerm.startsWith(author)) {
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
	private List<IBook> findByTitle(String title){
		List<IBook> bookTitle = getBooks();
		String[] split;

		if(title != null){
			//Going through all the bookList
			for(IBook book: bookList) {
				//***CHANGED***: see findByAuthor for detail
				split = book.getBookName().toLowerCase().split("[-. ,:]+");
				for(String splitTerm: split){
					//If the string inputted matches any of the terms in the book titles, then add the book to our return list
					if (splitTerm.startsWith(title)) {
						bookTitle.add(book);
					}
				}
			}
		}
		return bookTitle;
	}


	@Override
	public List<IBook> getBooks(){
		final List<IBook> books = new ArrayList<>();

		try (final Connection conn = connection()){
			final Statement stmt = conn.createStatement();
			final ResultSet rtst = stmt.executeQuery("SELECT * FROM BOOKS");

			while(rtst.next()){
				final Book book = createBook(rtst);
				//ADDED TO BOOKS ???
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
	public IBook insertBook(IBook book) {

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
			pstmt.setInt(9, book.getImage());
			pstmt.executeUpdate();

			return book;
		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}

	@Override
	public void updateBook(IBook book){

		try (final Connection conn = connection()){
			final PreparedStatement pstmt = conn.prepareStatement("UPDATE BOOKS SET quantity=?,price=?, reserve=?");
			pstmt.setInt(1, book.getStock());
			pstmt.setInt(2, book.getPrice());
			pstmt.setInt(3, book.getReserve());

		}
		catch(final SQLException e){
			throw new PersistenceException(e);
		}
	}

	@Override
	public void deleteBook(IBook book){
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